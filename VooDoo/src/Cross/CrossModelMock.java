package Cross;

/**
 * Created by Su on 22/03/17.
 */
public class CrossModelMock implements ICrossModel {
    private double x;
    private double y;
    private XYListener listener;

    @Override
    public double getMinX() {
        return 0;
    }

    @Override
    public double getMinY() {
        return 0;
    }

    @Override
    public double getMaxX() {
        return 100;
    }

    @Override
    public double getMaxY() {
        return 100;
    }

    @Override
    public void SetCurrent(double x, double y) {
        this.x = x;
        this.y = y;
        updateListeners();
    }

    @Override
    public double getCurrentX() {
        return x/(double)2;
    }

    @Override
    public double getCurrentY() {
        return y/(double)2;
    }

    @Override
    public void addXYListener(XYListener listener) {

        this.listener = listener;
        updateListeners();

    }

    private void updateListeners() {
        if(listener!=null)
            listener.XYUpdated(this,getCurrentX(),getCurrentY());
    }

    @Override
    public void removeXYListener(XYListener listener) {
        this.listener = null;
    }
}
