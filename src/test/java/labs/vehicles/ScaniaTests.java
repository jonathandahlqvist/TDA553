package labs.vehicles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTests {
    Scania scania;

    @BeforeEach
    void setUp(){
        scania = new Scania(Color.green, 0, 0);
    }

    @Test
    void testChangeAngleTooMuch(){
        scania.changeAngle(100);
        assertEquals(70, scania.getAngle());
    }

    @Test
    void testOpenBedWhileDriving(){
        scania.setCurrentSpeed(10);
        scania.changeAngle(20);
        assertEquals(0,scania.getAngle());
    }

    @Test
    void testDriveWhileOpen(){
        scania.setCurrentSpeed(0);
        scania.changeAngle(-1000);
        scania.changeAngle(5);
        assertEquals(5, scania.getAngle());
        scania.gas(5);
        assertEquals(0, scania.getCurrentSpeed());
    }
}
