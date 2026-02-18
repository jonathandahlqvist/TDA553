package labs.vehicles;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Vehicle implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color clr; // Color of the car
    private final Point2D.Double position;
    private int direction; // north = 0, east = 1, south = 2, west = 3

    public Vehicle(int nrDoors, double enginePower, Color clr, double x, double y){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.clr = clr;
        this.position = new Point2D.Double(x, y);
        this.direction = 0;
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

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public Color getColor(){
        return clr;
    }

    protected void setX(double x) {
        position.x = x;
    }

    protected void setY(double y) {
        position.y = y;
    }

    protected void setColor(Color clr) {
        this.clr = clr;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        setCurrentSpeed(0); }

    public int getDirection() {
        return direction;
    }

    public void gas(double amount){
        if (amount >= 0 && amount <= 1) {
            incrementSpeed(amount);
        } else if (amount > 1) {
            incrementSpeed(1);
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