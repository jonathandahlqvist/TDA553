package labs.vehicles;

import java.awt.geom.Point2D;

public class Element {
    protected final Point2D.Double position;

    public Element(double x, double y) {
        position = new Point2D.Double(x, y);
    }


    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    protected void setX(double x) {
        position.x = x;
    }

    protected void setY(double y) {
        position.y = y;
    }
}
