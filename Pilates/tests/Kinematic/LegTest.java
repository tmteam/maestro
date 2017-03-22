package Kinematic;

import Settings.LegSettings;
import Settings.ServoSettings;
import com.sun.tools.javac.util.Assert;
import com.tmteam.jamaestro.settings.ChannelSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Su on 16/03/17.
 */
class LegTest extends  LegTestBase {
    @Test
    void toAngles_MinPositions_convertsToMinAngels() {
        Leg leg = CreateLeg();

        LegServoPositions positions = new LegServoPositions(
                leg.getServoT0().getChannelSettings().getMinimum(),
                leg.getServoM1().getChannelSettings().getMinimum(),
                leg.getServoB2().getChannelSettings().getMinimum());

        LegAngles angles = leg.toAngles(positions);

        Assertions.assertEquals(leg.getServoT0().getServoSettings().getMinValue(),angles.t0,0.01);
        Assertions.assertEquals(leg.getServoM1().getServoSettings().getMinValue(),angles.m1,0.01);
        Assertions.assertEquals(leg.getServoB2().getServoSettings().getMinValue(),angles.b2,0.01);
    }
    @Test
    void toAngles_MaxPositions_convertsToMaxAngels() {
        Leg leg = CreateLeg();

        LegServoPositions positions = new LegServoPositions(
                leg.getServoT0().getChannelSettings().getMaximum(),
                leg.getServoM1().getChannelSettings().getMaximum(),
                leg.getServoB2().getChannelSettings().getMaximum());

        LegAngles angles = leg.toAngles(positions);

        Assertions.assertEquals(leg.getServoT0().getServoSettings().getMaxValue(),angles.t0,0.01);
        Assertions.assertEquals(leg.getServoM1().getServoSettings().getMaxValue(),angles.m1,0.01);
        Assertions.assertEquals(leg.getServoB2().getServoSettings().getMaxValue(),angles.b2,0.01);
    }
    @Test
    void toPositions_MinAngles_convertsToMinPositions() {
        Leg leg = CreateLeg();
        LegAngles minimumAngels =  new LegAngles(
                leg.getServoT0().getServoSettings().getMinValue(),
                leg.getServoM1().getServoSettings().getMinValue(),
                leg.getServoB2().getServoSettings().getMinValue());
        LegServoPositions positions = leg.toPositions(minimumAngels);
        Assertions.assertEquals(leg.getServoT0().getChannelSettings().getMinimum(),positions.t0);
        Assertions.assertEquals(leg.getServoM1().getChannelSettings().getMinimum(),positions.m1);
        Assertions.assertEquals(leg.getServoB2().getChannelSettings().getMinimum(),positions.b2);
    }
    @Test
    void toPositions_MaxAngles_convertsToMaxPositions() {
        Leg leg = CreateLeg();
        LegAngles maxAngles =  new LegAngles(
                leg.getServoT0().getServoSettings().getMaxValue(),
                leg.getServoM1().getServoSettings().getMaxValue(),
                leg.getServoB2().getServoSettings().getMaxValue());
        LegServoPositions positions = leg.toPositions(maxAngles);
        Assertions.assertEquals(leg.getServoT0().getChannelSettings().getMaximum(),positions.t0);
        Assertions.assertEquals(leg.getServoM1().getChannelSettings().getMaximum(),positions.m1);
        Assertions.assertEquals(leg.getServoB2().getChannelSettings().getMaximum(),positions.b2);
    }
    @Test
    void AverageAngles_convertToPositionsAndBack() {
        Leg leg = CreateLeg();
        final int averagePosition = 4000;

        LegServoPositions origin = new LegServoPositions(averagePosition, averagePosition, averagePosition);
        LegAngles someAngle =   leg.toAngles(origin);
        LegServoPositions calculated = leg.toPositions(someAngle);

        Assertions.assertEquals(origin.t0, calculated.t0);
        Assertions.assertEquals(origin.m1, calculated.m1);
        Assertions.assertEquals(origin.b2, calculated.b2);
    }
}