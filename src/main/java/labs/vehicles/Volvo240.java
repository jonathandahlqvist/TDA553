package labs.vehicles;

import java.awt.*;

public class Volvo240 extends Vehicle {

    private final static double trimFactor = 1.25;

    public Volvo240(Color clr, double x, double y){
        super(4, 140, clr, x, y);
    }

    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}