package labs.vehicles;

public interface VehicleState {
    void gas(Vehicle v, double amount);
    void brake(Vehicle v, double amount);
    void move(Vehicle v);
    void turnLeft(Vehicle v);
    void turnRight(Vehicle v);
}
