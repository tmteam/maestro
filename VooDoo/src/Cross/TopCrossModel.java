package Cross;

import Kinematic.Leg;
import Kinematic.LegAngles;

/**
 * Created by Su on 22/03/17.
 */
public class TopCrossModel extends CrossModelBase {

    public TopCrossModel(Leg leg){
        super(leg);

        LegAngles minYAngles = new LegAngles(90, 90, 180);
        double minY = leg.toPoint(minYAngles).y;
        LegAngles maxYAngles = new LegAngles(90,
                leg.getServoM1().getServoSettings().getMaxValue(),
                leg.getServoM1().getServoSettings().getMaxValue()-90
        );
        double maxY = leg.toPoint(maxYAngles).y;

        LegAngles minXAngles = new LegAngles(
                leg.getServoT0().getServoSettings().getMaxValue(), 180, 180);
        double minX = leg.toPoint(minXAngles).x;
        LegAngles maxXAngles = new LegAngles(0,180,180);
        double maxX = leg.toPoint(maxXAngles).x;

        setRanges(minX,minY, maxX, maxY);
        }
}
