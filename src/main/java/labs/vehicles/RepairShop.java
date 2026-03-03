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

    public Class<T> getSupportedType() {
        return supportedType;
    }

    private boolean checkCompatibility(Vehicle car) {
        return supportedType.isInstance(car);
    }

    public void tryAddVehicle(Vehicle car) {
        if (checkCompatibility(car) && !(car.getState() instanceof InShop)) {
            if (loaded.size() < capacity) {
                addVehicle(supportedType.cast(car));
            }
        }
    }

    private void addVehicle(T v) {
        v.stopEngine();
        loaded.add(v);
        v.setState(new InShop());
    }

    public void removeVehicle(T v) {
        if (!loaded.isEmpty() && loaded.contains(v)) {
            loaded.remove(v);
            v.setState(new OnRoad());
        }
    }
}