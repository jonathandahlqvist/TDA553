package labs.vehicles;

public class Ramp {
    private boolean lowered;

    public Ramp() {
        lowered = false;
    }

    public void raise() {
        lowered = false;
    }
    public void lower() {
        lowered = true;
    }
    public boolean isLowered() {
        return lowered;
    }
}

