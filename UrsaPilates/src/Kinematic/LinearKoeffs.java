package Kinematic;

public class LinearKoeffs {
    public LinearKoeffs(double k, double b){
        this.k = k;
        this.b = b;
    }
    public final double k;
    public final double b;
    public double calcFor(double x){
        return k*x+b;
    }
}
