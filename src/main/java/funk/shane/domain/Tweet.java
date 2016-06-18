package funk.shane.domain;

import java.util.Collections;
import java.util.List;

public class Tweet {

    private List<Statuses> statuses;

    public Tweet() {   }

    public Tweet(List<Statuses> statuses) {
        this.statuses = statuses;
    }

    public List<Statuses> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Statuses> statuses) {
        this.statuses = statuses;
    }

    public static Tweet getDefaultInstance() {
        return new Tweet(Collections.EMPTY_LIST);
    }
}
