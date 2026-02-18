package labs.vehicles;


import java.util.ArrayList;

public class RepairShop<T extends Vehicle> {
    private final int capacity;
    private ArrayList<T> loaded;

    public RepairShop(int capacity) {
        this.capacity = capacity;
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