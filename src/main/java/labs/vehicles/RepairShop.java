package labs.vehicles;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class RepairShop<T extends Vehicle> extends Element {
    private final Class<T> supportedType;
    private final int capacity;
    private ArrayList<T> loaded;

    public RepairShop(int capacity, Class<T> supportedType) {
        this(capacity, 0, 0, supportedType); // Default position
    }


    public RepairShop(int capacity, double x, double y, Class<T> supportedType) {
        super(x, y);
        this.capacity = capacity;
        this.supportedType = supportedType;
        loaded = new ArrayList<>();
    }

    public ArrayList<T> getLoaded() {
        return loaded;
    }

    public int getCapacity() {
        return capacity;
    }

    public void addVehicle(T v) {
        if (loaded.size() >= capacity) {
            throw new IllegalStateException("Repair shop is full");
        }

        loaded.add(v);
    }

    public void removeVehicle(T v) {
        if (loaded.isEmpty()) {
            throw new IllegalStateException("Repair shop is empty");
        }
        if (!loaded.contains(v)) {
            throw new IllegalArgumentException("Car is not in repair shop");
        }
        loaded.remove(v);
    }
}