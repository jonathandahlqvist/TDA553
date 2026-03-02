package labs.graphics;

import labs.vehicles.RepairShop;
import labs.vehicles.Saab95;
import labs.vehicles.Scania;
import labs.vehicles.Vehicle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Model {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    // private Timer timer = new Timer(delay, new Controller.TimerListener());
    private final Timer timer = new Timer(delay, new TimerListener());

    ArrayList<Vehicle> cars = new ArrayList<>();
    ArrayList<RepairShop<? extends Vehicle>> workshops = new ArrayList<>();

    private final ArrayList<Observer> observers = new ArrayList<>();

    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    public ArrayList<RepairShop<? extends Vehicle>> getWorkshops() {
        return workshops;
    }

    public void addObserver(Observer obs) {
        if (obs != null && !observers.contains(obs)) {
            observers.add(obs);
        }
    };

    public void removeObserver(Observer obs) {
        observers.remove(obs);
    };

    private void notifyChange() {
        for (Observer obs: observers) {
            obs.actOnChange();
        }
    };

    public void startTimer() {
        timer.start();
    }

    void gas(double amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.gas(gas);
        }
        notifyChange();
    }

    void brake(double amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars){
            car.brake(brake);
        }
        notifyChange();
    }

    void turboOn() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
        notifyChange();
    }
    void turboOff() {
        for (Vehicle car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
        notifyChange();
    }

    void changeAngle(int degrees) {
        for (Vehicle car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).changeAngle(degrees);
            }
        }
        notifyChange();
    }

    void startCars() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
        notifyChange();
    }
    void stopCars() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
        notifyChange();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        private final int lowerBorder = 500;
        private final int rightBorder = 700;

        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
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

                if ((y > lowerBorder-car.getCurrentSpeed() && direction == 0) ||
                        (y < car.getCurrentSpeed() && direction == 2) ||
                        (x > rightBorder-car.getCurrentSpeed() && direction == 1) ||
                        (x < car.getCurrentSpeed() && direction == 3))
                {
                    car.stopEngine();
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }
            }
            notifyChange();
        }
    }
}
