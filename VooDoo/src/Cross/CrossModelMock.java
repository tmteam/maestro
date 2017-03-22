package Cross;

/**
 * Created by Su on 22/03/17.
 */
public class CrossModelMock implements ICrossModel {
    private double targetX;
    private double targetY;
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
    public void setTarget(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        updateListeners();
    }

    @Override
    public double getTargetX() {
        return targetX;
    }

    @Override
    public double getTargetY() {
        return targetY;
    }

    @Override
    public double getCurrentX() {
        return targetX /(double)2;
    }

    @Override
    public double getCurrentY() {
        return targetY/(double)2;
    }

    @Override
    public void addXYListener(XYListener listener) {

        this.listener = listener;
        updateListeners();

    }

    private void updateListeners() {
        if (listener != null) {
            listener.currentXYUpdated(this, getCurrentX(), getCurrentY());
            listener.targetXYUpdated(this, getTargetX(),getTargetY());
        }
    }

    @Override
    public void removeXYListener(XYListener listener) {
        this.listener = null;
    }
}
