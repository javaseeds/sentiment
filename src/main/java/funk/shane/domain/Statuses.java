package funk.shane.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Statuses {
    private final static Logger log = LoggerFactory.getLogger(Statuses.class);

    @JsonProperty
    private String text;
    @JsonProperty
    private String created_at;
    @JsonProperty
    private Geo geo;
    @JsonProperty
    private User user;

    // calculated value
    private int score;

    public Statuses() {
     }

    public Statuses(String text, Geo geo, User user) {
        this.text = text;
        this.geo = geo;
        this.user = user;
   }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Geo getGeo() {
        return geo == null ? Geo.defaultGeo : geo;
    }
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        else if(obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Statuses other = (Statuses)obj;

        return Objects.equal(this.text, other.text)
                && Objects.equal(this.geo, other.geo)
                && Objects.equal(this.created_at, other.created_at)
                && Objects.equal(this.user, other.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(text, geo, user, created_at);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("text", text)
            .add("geo", geo)
            .add("user", user)
            .add("created_at", created_at)
            .toString();
    }
}
