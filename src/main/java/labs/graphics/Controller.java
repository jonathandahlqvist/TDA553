package labs.graphics;

import labs.vehicles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class Controller {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());


    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    ArrayList<Vehicle> cars = new ArrayList<>();
    ArrayList<RepairShop<? extends Vehicle>> workshops = new ArrayList<>();
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        Controller cc = new Controller();

        cc.cars.add(new Volvo240(Color.red, 0,0));
        cc.cars.add(new Saab95(Color.blue, 0, 100));
        cc.cars.add(new Scania(Color.red, 0, 200));
        RepairShop<Volvo240> volvoWorkshop = new RepairShop<>(100, 300, 0, Volvo240.class);
        volvoWorkshop.setImage("pics/VolvoBrand.jpg");
        cc.workshops.add(volvoWorkshop);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        private final int lowerBorder = 500;
        private final int rightBorder = 700;

        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                /*
                if (car.getY() > 500 || car.getY() < 0 || car.getX() > 500 || car.getX() < 0) {
                    car.turnRight();
                    car.turnRight();
                }*/

                car.move();

                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                int direction = car.getDirection();

                for (RepairShop<? extends Vehicle> workshop : workshops) {
                    if (car.getX() >= workshop.getX() - 25 && car.getX() <= workshop.getX() + 25 &&
                            car.getY() >= workshop.getY() - 25 && car.getY() <= workshop.getY() + 25) {
                            workshop.tryAddVehicle(car);
                    }
                }

                if (
                        (y > lowerBorder-car.getCurrentSpeed() && direction == 0) ||
                        (y < car.getCurrentSpeed() && direction == 2) ||
                        (x > rightBorder-car.getCurrentSpeed() && direction == 1) ||
                        (x < car.getCurrentSpeed() && direction == 3))
                        {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }
    void changeAngle(int degrees) {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).changeAngle(degrees);
            }
        }
    }

    void startCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }
    void stopCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }}
