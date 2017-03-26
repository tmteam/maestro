package Kinematic;

/**
 * Created by Su on 15/03/17.
 */
public class DimensionPoint {
    public final double x;
    public final double y;
    public final double z;

    public DimensionPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public DimensionPoint copy(){
        return new DimensionPoint(x,y,z);
    }
    public double distanceTo(DimensionPoint point){
        return  Math.sqrt(sqr(x-point.x)+ sqr(y- point.y) + sqr(z-point.z));
    }

    @Override
    public String toString(){
        return "{"+x+", "+y+", "+z+"}";
    }
    double sqr(double r){
        return  r*r;
    }
}
