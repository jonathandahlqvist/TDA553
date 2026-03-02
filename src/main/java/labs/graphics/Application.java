package labs.graphics;

import labs.vehicles.RepairShop;
import labs.vehicles.Saab95;
import labs.vehicles.Scania;
import labs.vehicles.Volvo240;

import java.awt.*;

public class Application {
    public static void main(String[] args) {
        Model model = createModel();
        View view = createViewForModel(model);
        Controller controller = new Controller(model, view);

        // Start the timer
        // cc.timer.start();
        model.startTimer();
    }

    public static Model createModel() {
        Model model = new Model();
        model.cars.add(new Volvo240(Color.red, 0,0));
        model.cars.add(new Saab95(Color.blue, 0, 100));
        model.cars.add(new Scania(Color.red, 0, 200));
        RepairShop<Volvo240> volvoWorkshop = new RepairShop<>(100, 300, 0, Volvo240.class);
        model.workshops.add(volvoWorkshop);

        return model;
    }

    public static View createViewForModel(Model model) {
        View view = new View("CarSim 1.0", model);
        model.addObserver(view);
        return view;
    }
}
