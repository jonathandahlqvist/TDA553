package labs.graphics;

import labs.vehicles.*;
import java.awt.*;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    Controller cc;

    // Initializes the panel
    public DrawPanel(int x, int y, Controller cc) {
        this.cc = cc;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle vehicle: cc.cars) {
            g.drawImage(vehicle.getImage(), (int) vehicle.getX(), (int) vehicle.getY(), null);
        }

        for (RepairShop workshop: cc.workshops) {
            g.drawImage(workshop.getImage(), (int) workshop.getX(), (int) workshop.getY(), null);
        }

    }
}
