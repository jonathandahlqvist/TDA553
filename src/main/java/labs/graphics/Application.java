package labs.graphics;

import java.awt.Color;

import labs.vehicles.Factory;

public class Application {
    public static void main(String[] args) {
        Model model = createModel();
        View view = createViewForModel(model);
        Controller controller = new Controller(model, view);

        // Start the timer
        model.startTimer();
    }

    private static Model createModel() {
        Model model = new Model();
        model.addVehicle(Factory.createVolvo(Color.red, 0,0));
        model.addVehicle(Factory.createSaab(Color.blue, 0, 100));
        model.addVehicle(Factory.createScania(Color.red, 0, 200));
        model.addRepairShop(Factory.createVolvoRepairShop(100, 300, 0));

        return model;
    }

    private static View createViewForModel(Model model) {
        View view = new View("CarSim 1.0", model);
        model.addObserver(view);
        return view;
    }
}
