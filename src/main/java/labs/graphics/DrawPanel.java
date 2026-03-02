package labs.graphics;

import labs.vehicles.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {
    // Initializes the panel
    private Model model;
    private final HashMap<Class<? extends Vehicle>, BufferedImage> vehicleImageMap;
    private final HashMap<Class<? extends Vehicle>, BufferedImage> repairShopImageMap;

    public DrawPanel(int x, int y, Model model) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.model = model;

        this.vehicleImageMap = createVehicleImageMap();
        this.repairShopImageMap = createRepairShopImageMap();
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vehicle vehicle: model.getCars()) {
            g.drawImage(getVehicleImage(vehicle), (int) vehicle.getX(), (int) vehicle.getY(), null);
        }

        for (RepairShop<? extends Vehicle> workshop: model.getWorkshops()) {
            g.drawImage(getRepairShopImage(workshop), (int) workshop.getX(), (int) workshop.getY(), null);
        }
    }

    private HashMap<Class<? extends Vehicle>, BufferedImage> createVehicleImageMap() {
        HashMap<Class<? extends Vehicle>, BufferedImage> map = new HashMap<>();

        map.put(Volvo240.class, readImage("pics/Volvo240.jpg"));
        map.put(Saab95.class, readImage("pics/Saab95.jpg"));
        map.put(Scania.class, readImage("pics/Scania.jpg"));

        return map;
    }

    private HashMap<Class<? extends Vehicle>, BufferedImage> createRepairShopImageMap() {
        HashMap<Class<? extends Vehicle>, BufferedImage> map = new HashMap<>();

        map.put(Volvo240.class, readImage("pics/VolvoBrand.jpg"));

        return map;
    }

    public BufferedImage readImage(String imageurl) {
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageurl)));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public BufferedImage getVehicleImage(Vehicle vehicle) {
        var vehicleClass = vehicle.getClass();
        return vehicleImageMap.get(vehicleClass);
    }

    public BufferedImage getRepairShopImage(RepairShop<? extends Vehicle> shop) {
        return repairShopImageMap.get(shop.getSupportedType());
    }
}
