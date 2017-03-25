package Cross;

import Kinematic.Leg;
import Kinematic.LegAngles;
import Kinematic.Servo;

import java.util.ArrayList;

/**
 * Created by Su on 22/03/17.
 */
public class CrossModelBase implements ICrossModel {
    private double targetX;
    private double targetY;
    private ArrayList<XYListener> listeners = new ArrayList<XYListener>();
    private Leg leg;

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double currentX;
    private double currentY;

    public CrossModelBase(Leg leg){
        this.leg = leg;

    }
    protected void  setRanges(double minX, double minY, double maxX , double maxY){
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMaxX() {
        return maxX;
    }

    @Override
    public double getMaxY() {
        return maxY;
    }
    public void  setCurrent(double x, double y){
        if(x==currentX && y == currentY)
            return;
        currentX = x;
        currentY = y;
        updateCurrentListeners();
    }
    @Override
    public void setTarget(double x, double y) {
        if(targetX==x && targetY == y)
            return;
        this.targetX = x;
        this.targetY = y;
        updateTargetListeners();
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
        return  currentX;
    }

    @Override
    public double getCurrentY() {
        return currentY;
    }

    @Override
    public void addXYListener(XYListener listener) {
        listeners.add(listener);
        updateSingleCurrent(listener);
        updateSingleTarget(listener);
    }
    private void updateCurrentListeners() {
        for(XYListener listener: listeners)
            updateSingleCurrent(listener);
    }
    private void updateTargetListeners() {
        for(XYListener listener: listeners)
            updateSingleTarget(listener);
    }


    private void updateSingleTarget(XYListener listener) {
        listener.targetXYUpdated(this, getTargetX(),getTargetY());
    }

    private void updateSingleCurrent(XYListener listener) {
        listener.currentXYUpdated(this, getCurrentX(), getCurrentY());
    }


    public void removeXYListener(XYListener listener) {
        listeners.remove(listener);
    }
}
