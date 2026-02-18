package labs.vehicles;

public class TruckBed {
    private int angle;

    public int getAngle() {
        return angle;
    }

    public void changeAngleHelper(int degrees){
        if ((angle + degrees) < 0) {
            angle = 0;
        } else {
            angle = Math.min((angle + degrees), 70);
        }
    }
}
