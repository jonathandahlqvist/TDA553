package labs.vehicles;

import java.awt.*;

public abstract class Vehicle extends Element implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color clr; // Color of the car
    private int direction;
    private boolean engineOn;// north = 0, east = 1, south = 2, west = 3
    private boolean isLoaded;

    public Vehicle(int nrDoors, double enginePower, Color clr, double x, double y){
        super(x, y);
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.clr = clr;
        this.direction = 1;
        this.isLoaded = false;
        stopEngine();
    }

    public void move() {
        switch (direction) {
            case 0:
                position.y += currentSpeed;
                break;
            case 1:
                position.x += currentSpeed;
                break;
            case 2:
                position.y -= currentSpeed;
                break;
            case 3:
                position.x-= currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        direction = (direction + 3) % 4;
    }

    public void turnRight() {
        direction = (direction + 1) % 4;
    }

    protected abstract double speedFactor();

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return clr;
    }

    protected void setColor(Color clr) {
        this.clr = clr;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public void startEngine(){
        if (!isLoaded && !engineOn) {
            currentSpeed = 0.1;
            engineOn = true;
        }
    }

    public void stopEngine(){
        setCurrentSpeed(0);
        engineOn = false;
    }

    public int getDirection() {
        return direction;
    }

    public void load() {
        isLoaded = true;
    }

    public void unload() {
        isLoaded = false;
    }

    public boolean getIsLoaded() {
        return isLoaded;
    }

    public void gas(double amount){
        if (engineOn) {
            if (amount >= 0 && amount <= 1) {
                incrementSpeed(amount);
            } else if (amount > 1) {
                incrementSpeed(1);
            }
        }
    }

    public void brake(double amount) {
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        } else if (amount > 1) {
            decrementSpeed(1);
        }
    }

    private void incrementSpeed(double amount) {
        setCurrentSpeed(Math.min((getCurrentSpeed() + speedFactor() * amount), getEnginePower()));
    }

    private void decrementSpeed(double amount) {
        setCurrentSpeed(Math.max((getCurrentSpeed() - speedFactor() * amount),0));
    }

}