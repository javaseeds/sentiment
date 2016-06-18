package funk.shane.views;

import funk.shane.domain.Tweet;
import io.dropwizard.views.View;

public class IndexView extends View {
    private Tweet tweets;

    public IndexView(Tweet tweets) {
        super("index.ftl");

        try {
            this.tweets = tweets;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tweet getTweets() {
        return tweets;
    }
}
