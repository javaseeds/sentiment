package funk.shane.resource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import funk.shane.domain.OAuthConnectFactory;
import funk.shane.domain.Tweet;
import funk.shane.security.OAuthConnector;
import funk.shane.util.SentimentScoring;
import funk.shane.views.IndexView;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
public class IndexResource {
    private static final Logger LOG = LoggerFactory.getLogger(IndexResource.class);
    private OAuthConnectFactory oAuthConnectFactory;

    public IndexResource(final OAuthConnectFactory oAuthConnectFactory) {
        this.oAuthConnectFactory = oAuthConnectFactory;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public IndexView index() {
        return new IndexView(new Tweet());
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public IndexView index(@FormParam("queryTerm") String queryTerm) {
        if(StringUtils.isBlank(queryTerm)) {
            return new IndexView(new Tweet());
        }
        else {
            LOG.info("> index()");
            Tweet tweets;
            try {
                tweets = processTweets(queryTerm);
            } catch (IOException e) {
                LOG.error("Bad thing occurred: {}", e.getMessage());
                e.printStackTrace();
                tweets = Tweet.getDefaultInstance();
            }

            return new IndexView(tweets);
        }
    }

    /**
     * TODO - remove from resource and put in processing package
     * @return Tweet
     * @throws IOException
     */
    private Tweet processTweets(final String queryTerm) throws IOException {
        LOG.info("+ + +> processTweets");
        TweetRetrieval tr = new TweetRetrieval(oAuthConnectFactory, OAuthConnector.getOAuthConsumer(oAuthConnectFactory), queryTerm);
        String tweetStr = tr.getTweetJSON();

        LOG.info("+ + +> tweets size: {}", tweetStr.length());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Tweet tweets = mapper.readValue(tweetStr, Tweet.class);

        return SentimentScoring.setScoring(tweets, queryTerm);
    }
}
