package Cross;

import Kinematic.Leg;
import Kinematic.LegAngles;
import Kinematic.Servo;

import java.util.ArrayList;

/**
 * Created by Su on 22/03/17.
 */
public class FrontCrossModel extends CrossModelBase {

    public FrontCrossModel(Leg leg){
        super(leg);

        LegAngles maxYAngles = new LegAngles(
                90,
                leg.getServoM1().getServoSettings().getMinValue(),
                180);
        double maxY= leg.toPoint(maxYAngles).z;

        LegAngles minYAngles = new LegAngles(90, 180, 180);
        double minY= leg.toPoint(minYAngles).z;

        LegAngles minXAngles = new LegAngles(
                leg.getServoT0().getServoSettings().getMaxValue(), 180, 180);
        double minX = leg.toPoint(minXAngles).x;

        LegAngles maxXAngles = new LegAngles(0,180,180);
        double maxX = leg.toPoint(maxXAngles).x;
        setRanges(minX,minY, maxX, maxY);
    }
}
