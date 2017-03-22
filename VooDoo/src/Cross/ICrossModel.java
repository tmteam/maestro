package Cross;

public interface ICrossModel{

    double getMinX();
    double getMinY();

    double getMaxX();
    double getMaxY();

    void setTarget(double x, double y);

    double getTargetX();
    double getTargetY();


    double getCurrentX();
    double getCurrentY();

    void addXYListener(XYListener listener);
    void removeXYListener(XYListener listener);
}
