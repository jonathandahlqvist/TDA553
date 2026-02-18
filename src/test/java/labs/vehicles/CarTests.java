package labs.vehicles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

class CarTests {
    Volvo240 volvo;
    Saab95 saab;

    @BeforeEach
    void setUp() {
        volvo = new Volvo240(Color.black, 0, 0);
        saab = new Saab95(Color.red, 0, 0);
    }

    @Test
    void testDirection() {
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnRight();
        volvo.turnLeft();
        assertEquals(2, volvo.getDirection());
    }

    @Test
    void testStartSpeed() {
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void testGetSetColor() {
        volvo.setColor(Color.cyan);
        assertEquals(Color.cyan,volvo.getColor());
    }

    @Test
    void testGetNrDoors() {
        int doors = volvo.getNrDoors();
        assertEquals(4, doors);
    }

    @Test
    void testGetEnginePower() {
        double engine = volvo.getEnginePower();
        assertEquals(100, engine);
    }

    @Test
    void testGetCurrentSpeed() {
        volvo.setCurrentSpeed(10);
        double speed = volvo.getCurrentSpeed();
        assertEquals(10, speed);
    }

    @Test
    void testGasVolvo() {
        volvo.gas(0.5);
        assertEquals(1.25/2, volvo.getCurrentSpeed());
    }
    @Test
    void testBrakeVolvo() {
        volvo.setCurrentSpeed(10);
        volvo.brake(0.5);
        assertEquals(10-1.25/2, volvo.getCurrentSpeed());
    }

    @Test
    void testGasSaab() {
        saab.setTurboOn();
        saab.gas(0.8);
        assertEquals(0.8*1.25*1.3, saab.getCurrentSpeed());
    }


    @Test
    void testBrakeSaab() {
        saab.setCurrentSpeed(10);
        saab.brake(0.8);
        assertEquals(10-0.8*1.25, saab.getCurrentSpeed());
    }

    @Test
    void testGasSaabMore1() {
        saab.gas(2);
        assertEquals(1*1.25, saab.getCurrentSpeed());
    }
    @Test
    void testBrakeVolvoMore1() {
        volvo.setCurrentSpeed(10);
        volvo.brake(1);
        assertEquals(10-1*1.25, volvo.getCurrentSpeed());
    }
    @Test
    void testBrakeSaabLess1() {
        volvo.setCurrentSpeed(10);
        volvo.brake(-10);
        assertEquals(10, volvo.getCurrentSpeed());
    }

    @Test
    void testGasVolvoLess1() {
        volvo.setCurrentSpeed(10);
        volvo.gas(-10);
        assertEquals(10, volvo.getCurrentSpeed());
    }

    @Test
    void testSaabTurboOn() {
        saab.setTurboOn();
        assertTrue(saab.getTurbo());
    }

    @Test
    void testSaabTurboOff() {
        saab.setTurboOff();
        assertFalse(saab.getTurbo());
    }

    @Test
    void testStartEngine() {
        saab.startEngine();
        assertEquals(0.1, saab.getCurrentSpeed());
    }

    @Test
    void testStopEngine() {
        saab.setCurrentSpeed(10);
        saab.stopEngine();
        assertEquals(0, saab.getCurrentSpeed());
    }


    @Test
    void testVolvoSpeedFactor() {
        double speedFactor = volvo.speedFactor();
        assertEquals(1.25, speedFactor);
    }

    @Test
    void testSaabSpeedFactor1() {
        double speedFactor = saab.speedFactor();
        assertEquals(1.25, speedFactor);
    }

    @Test
    void testSaabSpeedFactor2() {
        saab.setTurboOn();
        double speedFactor = saab.speedFactor();
        assertEquals(1.25*1.3, speedFactor);
    }

    @Test
    void testVolvoMaxSpeed() {
        volvo.setCurrentSpeed(volvo.getEnginePower());
        volvo.gas(1);
        assertEquals(volvo.getEnginePower(), volvo.getCurrentSpeed());
    }

    @Test
    void testVolvoMinSpeed() {
        volvo.setCurrentSpeed(0);
        volvo.brake(1);
        assertEquals(0, volvo.getCurrentSpeed());
    }

    @Test
    void testSaabMaxSpeed() {
        saab.setCurrentSpeed(saab.getEnginePower());
        saab.gas(1);
        assertEquals(saab.getEnginePower(), saab.getCurrentSpeed());
    }

    @Test
    void testSaabMinSpeed() {
        saab.setCurrentSpeed(0);
        saab.brake(1);
        assertEquals(0, saab.getCurrentSpeed());
    }

    @Test
    void testMove() {
        volvo.setCurrentSpeed(1);
        for (int i = 0; i<4; i++) {
            volvo.move();
            volvo.turnRight();
        }
        assertEquals(0, volvo.getX());
        assertEquals(0, volvo.getY());

    }

}