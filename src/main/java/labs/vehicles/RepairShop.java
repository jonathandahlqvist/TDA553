package labs.vehicles;

import java.util.ArrayList;

public class RepairShop<T extends Vehicle> extends Element {
    private final Class<T> supportedType;
    private final int capacity;
    private final ArrayList<T> loaded;

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

    private boolean checkCompatibility(Vehicle car) {
        return supportedType.isInstance(car);
    }

    public void tryAddVehicle(Vehicle car) {
        if (checkCompatibility(car) && !(car.getIsLoaded())) {
            if (loaded.size() < capacity) {
                addVehicle(supportedType.cast(car));
            } else {
                throw new IllegalStateException("Repair shop is full");
            }
        }
    }

    private void addVehicle(T v) {
        v.stopEngine();
        v.load();
        loaded.add(v);
    }

    public void removeVehicle(T v) {
        if (loaded.isEmpty()) {
            throw new IllegalStateException("Repair shop is empty");
        }
        if (!loaded.contains(v)) {
            throw new IllegalArgumentException("Car is not in repair shop");
        }
        v.unload();
        loaded.remove(v);
    }
}