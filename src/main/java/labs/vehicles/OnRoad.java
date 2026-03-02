package labs.vehicles;

public class OnRoad implements VehicleState{
    @Override
    public void gas(Vehicle v, double amount) {
        if (v.isEngineOn()) {
            if (amount >= 0 && amount <= 1) {
                v.incrementSpeed(amount);
            } else if (amount > 1) {
                v.incrementSpeed(1);
            }
        }
    }
    @Override
    public void brake(Vehicle v, double amount) {
        if (amount >= 0 && amount <= 1) {
            v.decrementSpeed(amount);
        } else if (amount > 1) {
            v.decrementSpeed(1);
        }
    }

    @Override
    public void move(Vehicle v) {
        v.moveNormally();
    }

    @Override
    public void turnLeft(Vehicle v) {
        v.rotateLeft();
    }

    @Override
    public void turnRight(Vehicle v) {
        v.rotateRight();}
}
