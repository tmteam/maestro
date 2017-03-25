package Cross;

import Kinematic.Leg;
import Kinematic.LegAngles;
import Kinematic.Servo;
import com.tmteam.jamaestro.settings.Serializer;

import java.util.*;

/**
 * Created by Su on 22/03/17.
 */
public class SideCrossModel implements ICrossModel {
    private double targetX;
    private double targetY;
    private ArrayList<XYListener> listeners = new ArrayList<XYListener>();
    private Servo xServo;
    private Servo yServo;
    private Leg leg;

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    public SideCrossModel(Leg leg){
        this.leg = leg;
        LegAngles maxYAngles = new LegAngles(
                90,
                leg.getServoM1().getServoSettings().getMinValue(),
                180);
        maxY= leg.toPoint(maxYAngles).z;

        LegAngles minYAngles = new LegAngles(90, 180, 180);
        minY= leg.toPoint(minYAngles).z;

        LegAngles minXAngles = new LegAngles(90, 90, 180);
        minX = leg.toPoint(minXAngles).y;

        LegAngles maxXAngles = new LegAngles(90,
                leg.getServoM1().getServoSettings().getMaxValue(),
                leg.getServoM1().getServoSettings().getMaxValue()-90
                );
        maxX = leg.toPoint(maxXAngles).y;
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
        listeners.add(listener);
        updateSingleListener(listener);
    }

    private void updateListeners() {
       for(XYListener listener: listeners)
       {
           updateSingleListener(listener);
       }
    }

    private void updateSingleListener(XYListener listener) {
        listener.currentXYUpdated(this, getCurrentX(), getCurrentY());
        listener.targetXYUpdated(this, getTargetX(),getTargetY());
    }


    public void removeXYListener(XYListener listener) {
        listeners.remove(listener);
    }
}
