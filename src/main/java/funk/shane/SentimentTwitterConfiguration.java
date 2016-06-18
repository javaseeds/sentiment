package funk.shane;

import com.fasterxml.jackson.annotation.JsonProperty;
import funk.shane.domain.OAuthConnectFactory;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class SentimentTwitterConfiguration extends Configuration {

    @NotNull
    private OAuthConnectFactory oauth;

    @JsonProperty("OAuth")
    public OAuthConnectFactory getOauth() {
        return oauth;
    }

    @JsonProperty("OAuth")
    public void setOauth(OAuthConnectFactory oauth) {
        this.oauth = oauth;
    }
}
