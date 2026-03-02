package labs.vehicles;

import java.awt.*;

public abstract class Vehicle extends Element implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color clr; // Color of the car
    private int direction;
    private boolean engineOn;// north = 0, east = 1, south = 2, west = 3
    private VehicleState state;

    public Vehicle(int nrDoors, double enginePower, Color clr, double x, double y){
        super(x, y);
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = 0;
        this.clr = clr;
        this.direction = 1;
        this.state = new OnRoad();
        stopEngine();
    }


    //Base methods
    public void gas(double amount){
        state.gas(this, amount);
    }

    public void brake(double amount) {
        state.brake(this, amount);
    }

    public void move() {
        state.move(this);
    }

    public void turnLeft() {
        state.turnLeft(this);
    }

    public void turnRight() {
        state.turnRight(this);
    }




    // Internal helper methods
    protected void rotateLeft() {
        direction = (direction + 3) % 4;
    }

    protected void rotateRight() {
        direction = (direction + 1) % 4;
    }

    protected void moveNormally() {
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

    protected void incrementSpeed(double amount) {
        setCurrentSpeed(Math.min((getCurrentSpeed() + speedFactor() * amount), getEnginePower()));
    }

    protected void decrementSpeed(double amount) {
        setCurrentSpeed(Math.max((getCurrentSpeed() - speedFactor() * amount),0));
    }

    protected abstract double speedFactor();



    //Getters
    public VehicleState getState() {
        return state;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public boolean isEngineOn() {
        return engineOn;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return clr;
    }



    //Setters
    public void setState(VehicleState state){
        this.state = state;
    }

    protected void setColor(Color clr) {
        this.clr = clr;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public void startEngine(){
        if (state instanceof OnRoad && !engineOn) {
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


}
