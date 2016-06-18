package funk.shane.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class OAuthConnectFactory {
    @NotEmpty
    private transient String accessToken;

    @NotEmpty
    private transient String accessSecret;

    @NotEmpty
    private transient String consumerKey;

    @NotEmpty
    private transient String consumerSecret;

    @JsonProperty
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonProperty
    public String getAccessSecret() {
        return accessSecret;
    }

    @JsonProperty
    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    @JsonProperty
    public String getConsumerKey() {
        return consumerKey;
    }

    @JsonProperty
    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    @JsonProperty
    public String getConsumerSecret() {
        return consumerSecret;
    }

    @JsonProperty
    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }
}
