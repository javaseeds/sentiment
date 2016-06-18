package funk.shane.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

public class User {
    @JsonProperty
    private String name;
    @JsonProperty
    private String location;

    public User() {
    }

    public User(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location == null ? "" : location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", name)
            .add("location", location)
            .toString();
    }
}
