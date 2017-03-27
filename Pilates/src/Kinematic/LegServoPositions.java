package Kinematic;

/**
 * Created by Su on 15/03/17.
 */
public class LegServoPositions {
    public final int t0;
    public final int m1;
    public final int b2;

    public LegServoPositions(int t0, int m1, int b2) {
        this.t0 = t0;
        this.m1 = m1;
        this.b2 = b2;
    }
    @Override
    public String toString(){
        return "{"+t0+", "+m1+", "+b2+"}";
    }


}
