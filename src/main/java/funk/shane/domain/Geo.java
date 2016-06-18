package funk.shane.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;

public class Geo {
    @JsonProperty
    private Point point;

    public Geo() {
    }

    public Geo(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    // handle the freemarker null annoyances
    public static Geo defaultGeo = new Geo(new Point(0,0));
}
