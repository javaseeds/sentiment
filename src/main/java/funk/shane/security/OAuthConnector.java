package funk.shane.security;

import funk.shane.domain.OAuthConnectFactory;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

public class OAuthConnector {
    public static OAuthConsumer getOAuthConsumer(final OAuthConnectFactory oAuthConnectFactory) {
        return new CommonsHttpOAuthConsumer(oAuthConnectFactory.getConsumerKey(), oAuthConnectFactory.getConsumerSecret());
    }
}

