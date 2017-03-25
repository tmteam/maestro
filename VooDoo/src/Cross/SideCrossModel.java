package Cross;

import Kinematic.Leg;
import Kinematic.LegAngles;
import Kinematic.Servo;
import com.tmteam.jamaestro.settings.Serializer;

import java.util.*;

/**
 * Created by Su on 22/03/17.
 */
public class SideCrossModel extends CrossModelBase {
    public SideCrossModel(Leg leg){
        super(leg);
        LegAngles maxYAngles = new LegAngles(
                90,
                leg.getServoM1().getServoSettings().getMinValue(),
                180);
        double maxY= leg.toPoint(maxYAngles).z;

        LegAngles minYAngles = new LegAngles(90, 180, 180);
        double minY= leg.toPoint(minYAngles).z;

        LegAngles minXAngles = new LegAngles(90, 90, 180);
        double minX = leg.toPoint(minXAngles).y;

        LegAngles maxXAngles = new LegAngles(90,
                leg.getServoM1().getServoSettings().getMaxValue(),
                leg.getServoM1().getServoSettings().getMaxValue()-90
                );
        double maxX = leg.toPoint(maxXAngles).y;
        setRanges(minX,minY, maxX, maxY);
    }
}
