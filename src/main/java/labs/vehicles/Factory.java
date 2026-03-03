package labs.vehicles;

import java.awt.Color;

public class Factory {
    public static Volvo240 createVolvo(Color clr, double x, double y) {
        return new Volvo240(clr, x, y);
    }

    public static Saab95 createSaab(Color clr, double x, double y) {
        return new Saab95(clr, x, y);
    }

    public static Scania createScania(Color clr, double x, double y) {
        return new Scania(clr, x, y);
    }

    public static CarTransport createCarTransport(Color clr, double x, double y) {
        return new CarTransport(clr, x, y);
    }

    public static RepairShop<Volvo240> createVolvoRepairShop(int capacity, double x, double y) {
        return new RepairShop<>(capacity, x, y, Volvo240.class);
    }

}
