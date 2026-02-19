package labs.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class RepairShopTest {
    RepairShop<Vehicle> vehicleshop;
    Volvo240 volvo;
    Saab95 saab;

    @BeforeEach
    void setUp() {
        vehicleshop = new RepairShop<>(1, Vehicle.class);
        volvo = new Volvo240(Color.cyan, 0, 0);
        saab = new Saab95(Color.orange, 0, 0);
    }

    @Test
    void testOvercapacityException() {
        vehicleshop.tryAddVehicle(volvo);
        assertThrows(IllegalStateException.class, () -> vehicleshop.tryAddVehicle(saab));
    }

    @Test
    void testRemoveEmptyShop() {
        assertThrows(IllegalStateException.class, () -> vehicleshop.removeVehicle(saab));
    }

    @Test
    void removeCarNotInShop() {
        vehicleshop.tryAddVehicle(volvo);
        assertThrows(IllegalArgumentException.class, () -> vehicleshop.removeVehicle(saab));
    }
}
