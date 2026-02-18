package labs;

import java.awt.*;
import java.util.Stack;

public class CarTransport extends Vehicle {
    private Ramp ramp;
    private final Stack<Vehicle> parkedCars;

    public CarTransport(int nrDoors, double enginePower, Color clr, double x, double y) {
        super(nrDoors, enginePower, clr, x, y);
        ramp = new Ramp();
        parkedCars = new Stack<>();
    }

    public void lowerRamp(){
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Current speed must be 0");
        }

        ramp.lower();
    }

    public void raiseRamp(){
        if (getCurrentSpeed() != 0) {
            throw new IllegalStateException("Current speed must be 0");
        }

        ramp.raise();
    }

    public void addCar(Vehicle car){
        if (car instanceof CarTransport) {
            throw new IllegalArgumentException("Cannot load another CarTransport");
        }

        if (Math.abs(car.getX() - this.getX()) >= 10
                || Math.abs(car.getY() - this.getY()) >= 10) {
            throw new IllegalArgumentException("Car too far away");
        }

        if (!ramp.isLowered()) {
            throw new IllegalStateException("Ramp is not lowered");
        }

        parkedCars.push(car);
        car.setX(this.getX());
        car.setY(this.getY());
    }

    public Stack<Vehicle> getParkedCars() {
        return parkedCars;
    }
    public void removeCar(){
        if (!ramp.isLowered()) {
            throw new IllegalStateException("Ramp is not lowered");
        }

        Vehicle P = parkedCars.pop();
        P.setX(this.getX()+1);
        P.setY(this.getY());
    }

    @Override
    public void move() {
        super.move();
        for (Vehicle p: parkedCars) {
            p.setPosition(pos[0], pos[1]);
        }
    }

    protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
}