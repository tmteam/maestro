package Cross;

public interface ICrossModel{

    double getMinX();
    double getMinY();

    double getMaxX();
    double getMaxY();

    void SetCurrent(double x, double y);

    double getCurrentX();
    double getCurrentY();

    void addXYListener(XYListener listener);
    void removeXYListener(XYListener listener);
}
