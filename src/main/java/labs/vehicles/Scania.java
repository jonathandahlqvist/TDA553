package labs.vehicles;

import java.awt.*;

public class Scania extends Vehicle {
    private final TruckBed truckbed;

    public Scania(Color clr, double x, double y){
        super(2,420,clr, x, y);
        truckbed = new TruckBed();
    }

    public void changeAngle(int degrees) {
        if (getCurrentSpeed() == 0) {
            truckbed.changeAngleHelper(degrees);
        }
    }

    public int getAngle() {
        return truckbed.getAngle();
    }

    protected double speedFactor(){
        return getEnginePower() * 0.005;
    }

    @Override
    public void gas(double amount) {
        if (truckbed.getAngle() == 0) {
            super.gas(amount);
        } else {
            System.out.println("Cannot start: Truck bed is up");
        }
    }

}

