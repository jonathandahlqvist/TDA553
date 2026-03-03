package labs.graphics;

import labs.vehicles.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Model {
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    private final int vehicleCapacity = 10;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private final ArrayList<RepairShop<? extends Vehicle>> repairShops = new ArrayList<>();

    private final ArrayList<Observer> observers = new ArrayList<>();

    ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    void addVehicle(Vehicle v) {
        if (vehicles.size() <= vehicleCapacity) {
            vehicles.add(v);
        }
    }

    void addRepairShop(RepairShop<? extends Vehicle> shop) {
        repairShops.add(shop);
    }

    void createRandomCar() {
        Vehicle car = Factory.createVolvo(Color.red, (int) (Math.random()*700), (int) (Math.random()*500));
        addVehicle(car);
        notifyChange();
    }

    void removeCar() {
        Vehicle lastVehicle = vehicles.getLast();
        if (!(lastVehicle.getState() instanceof InShop)) {
            vehicles.remove(lastVehicle);
        }
        notifyChange();
    }

    ArrayList<RepairShop<? extends Vehicle>> getRepairShops() {
        return repairShops;
    }

    void addObserver(Observer obs) {
        if (obs != null && !observers.contains(obs)) {
            observers.add(obs);
        }
    };

    void removeObserver(Observer obs) {
        observers.remove(obs);
    };

    private void notifyChange() {
        for (Observer obs: observers) {
            obs.actOnChange();
        }
    };

    void startTimer() {
        timer.start();
    }

    void gas(double amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : vehicles) {
            car.gas(gas);
        }
        notifyChange();
    }

    void brake(double amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle car : vehicles){
            car.brake(brake);
        }
        notifyChange();
    }

    void turboOn() {
        for (Vehicle car : vehicles) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
        notifyChange();
    }

    void turboOff() {
        for (Vehicle car : vehicles) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
        notifyChange();
    }

    void changeAngle(int degrees) {
        for (Vehicle car : vehicles) {
            if (car instanceof Scania) {
                ((Scania) car).changeAngle(degrees);
            }
        }
        notifyChange();
    }

    void startCars() {
        for (Vehicle car : vehicles) {
            car.startEngine();
        }
        notifyChange();
    }
    void stopCars() {
        for (Vehicle car : vehicles) {
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
            for (Vehicle car : vehicles) {
                car.move();

                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                int direction = car.getDirection();

                for (RepairShop<? extends Vehicle> workshop : repairShops) {
                    if (car.getX() >= workshop.getX() - 50 && car.getX() <= workshop.getX() + 50 &&
                            car.getY() >= workshop.getY() - 50 && car.getY() <= workshop.getY() + 50) {
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
