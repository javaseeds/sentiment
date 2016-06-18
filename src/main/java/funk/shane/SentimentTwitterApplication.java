package funk.shane;

import funk.shane.resource.IndexResource;
import funk.shane.health.DefaultHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SentimentTwitterApplication extends Application<SentimentTwitterConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(SentimentTwitterApplication.class);

    public static void main(String[] args) throws Exception {
        new SentimentTwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SentimentTwitterConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/assets/"));
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(SentimentTwitterConfiguration sentimentTwitterConfiguration, Environment environment) throws Exception {
        LOG.info("> > > Application {} run - beginning TweetRetrieval", getName());
        //TODO add something here

        environment.healthChecks().register("", new DefaultHealthCheck());
        environment.jersey().register(new IndexResource(sentimentTwitterConfiguration.getOauth()));
    }

    @Override
    public String getName() {
        return "sentiment-twitter";
    }
}
