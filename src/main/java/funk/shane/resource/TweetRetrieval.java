package funk.shane.resource;

import funk.shane.domain.OAuthConnectFactory;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TweetRetrieval {
    private static final Logger LOG = LoggerFactory.getLogger(TweetRetrieval.class);

    private static final String twitterReqQry = "https://api.twitter.com/1.1/search/tweets.json?q=%%23%s&result_type=recent&lang=en&count=100";

    private OAuthConsumer oAuthConsumer;
    private OAuthConnectFactory oAuthConnectFactory;
    private String queryTerm;

    public TweetRetrieval(final OAuthConnectFactory oAuthConnectFactory, final OAuthConsumer oAuthConsumer, final String queryTerm) {
        this.oAuthConnectFactory = oAuthConnectFactory;
        this.oAuthConsumer = oAuthConsumer;
        this.queryTerm = queryTerm;
    }

    public String getTweetJSON() {
        LOG.info("* getTweetStr called with ConsumerKey: {}", oAuthConsumer.getConsumerKey());
        oAuthConsumer.setTokenWithSecret(oAuthConnectFactory.getAccessToken(), oAuthConnectFactory.getAccessSecret());
        HttpGet request = new HttpGet(String.format(twitterReqQry, queryTerm));

        try {
            oAuthConsumer.sign(request);
        } catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException e) {
            LOG.error("oAuthConsumer: {}", e.getMessage());
        }

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;

        try {
            response = client.execute(request);
        } catch (IOException e) {
            LOG.error("response: {}", e.getMessage());
        }

        LOG.info("* * response status: {}", response.getStatusLine().getStatusCode());

        String tweets = "";
        try {
            tweets = IOUtils.toString(response.getEntity().getContent());
        } catch (IOException e) {
            LOG.error("tweets (getContent()): {}", e.getMessage());
        }

        return tweets;
    }
}
