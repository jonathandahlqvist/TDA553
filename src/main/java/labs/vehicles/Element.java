package labs.vehicles;

import labs.graphics.DrawPanel;
import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Element {
    protected BufferedImage image;
    protected final Point2D.Double position;

    public Element(double x, double y) {
        position = new Point2D.Double(x, y);
    }

    public void setImage(String imageurl) {
        try {
            this.image = ImageIO.read(DrawPanel.class.getResourceAsStream(imageurl));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
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
