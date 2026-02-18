package labs.lab3;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

class ImagePoint {
    BufferedImage image;
    Point2D.Double pos = new Point2D.Double();

    public ImagePoint(BufferedImage image) {
        this.image = image;
    }

    public ImagePoint(BufferedImage image, double x, double y) {
        this.image = image;
        pos.x = x;
        pos.y = y;
    }

    public double getX() {
        return pos.x;
    }

    public double getY() {
        return pos.y;
    }

    public void setX(double x) {
        pos.x = x;
    }

    public void setY(double y) {
        pos.y = y;
    }
}

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage volvoWorkshopImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    // To keep track of a single car's position
    ArrayList<ImagePoint> vehicles = new ArrayList<>();
    ArrayList<ImagePoint> buildings = new ArrayList<>();

    // TODO: Make this general for all cars
    void moveit(int x, int y){
        volvoPoint.x = x;
        volvoPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));

            vehicles.add(new ImagePoint(volvoImage));
            vehicles.add(new ImagePoint(saabImage));
            vehicles.add(new ImagePoint(scaniaImage));
            buildings.add(new ImagePoint(volvoWorkshopImage, 300, 300));


        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (ImagePoint vehicle: vehicles) {
            g.drawImage(vehicle.image, (int) vehicle.getX(), (int) vehicle.getY(), null);
        }

        for (ImagePoint building: buildings) {
            g.drawImage(building.image, (int) building.getX(), (int) building.getY(), null);
        }

    }
}
