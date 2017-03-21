package Kinematic;

/**
 * Created by Su on 15/03/17.
 */
public class LegAngles {
    public final double t0;
    public final double m1;
    public final double b2;

    public LegAngles(double t0, double m1, double b2) {
        this.t0 = t0;
        this.m1 = m1;
        this.b2 = b2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegAngles angles = (LegAngles) o;

        if (Double.compare(angles.t0, t0) != 0) return false;
        if (Double.compare(angles.m1, m1) != 0) return false;
        return Double.compare(angles.b2, b2) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t0);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m1);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b2);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String toString(){
        return "{"+t0+", "+m1+", "+b2+"}";
    }
}
