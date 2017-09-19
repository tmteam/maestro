package Kinematic;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return "{"+ String.format("%.0f",x)+", "+String.format("%.0f",y)+", "+String.format("%.0f",z)+"}";
    }
    public Boolean IsSimilarTo(DimensionPoint point, int places){
        if(round(point.x, places)!= round(x, places))
            return false;
        if(round(point.y, places)!= round(y, places))
            return false;
        if(round(point.z, places)!= round(z, places))
            return false;
        return true;
    }
    double sqr(double r){
        return  r*r;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
