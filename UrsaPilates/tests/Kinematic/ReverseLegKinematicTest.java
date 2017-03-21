package Kinematic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Su on 16/03/17.
 */
public class ReverseLegKinematicTest extends LegTestBase {
    @Test
    void legIsLiftedUp(){
        //impossible angles in real world
        checkAnglesViaDirectKinematic(90, 290, 170);
    }

    @Test
    void stancePosition(){
        checkAnglesViaDirectKinematic(100, 160, 150);
    }

    @Test
    void legFrontPhasePosition(){
        checkAnglesViaDirectKinematic(95, 190, 100);
    }

    @Test
    void legIsPreloaded(){
        checkAnglesViaDirectKinematic(85, 85, 20);
    }

    void checkAnglesViaDirectKinematic(double t0Angle, double m1Angle, double b2Angle){
        Leg leg = CreateLeg();
        //direct kinematic
        DimensionPoint point =  leg.toPoint(new LegAngles(t0Angle, m1Angle, b2Angle));
        //reverse kinematic
        LegAngles angles = leg.toAngles(point);

        Assertions.assertEquals(t0Angle, angles.t0, 0.01);
        Assertions.assertEquals(m1Angle, angles.m1,0.01);
        Assertions.assertEquals(b2Angle, angles.b2,0.01);
    }

    @Test //90 270 180
    void horizontalAlongTheBodyToFront(){
        Leg leg = CreateLeg();

        double legLength =
                + leg.getSettings().getMiddleLength()
                        + leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                - legLength,
                - leg.getSettings().getTopToMiddleVerticalOffset());

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(90,  angles.t0,0.01);
        Assertions.assertEquals(270, angles.m1,0.01);
        Assertions.assertEquals(180,   angles.b2,0.01);
    }

    @Test //90 90 180
    void horizontalAlongTheBodyToBack(){
        Leg leg = CreateLeg();

        double legLength =
                        + leg.getSettings().getMiddleLength()
                        + leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                legLength,
                -leg.getSettings().getTopToMiddleVerticalOffset());

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(90,  angles.t0,0.01);
        Assertions.assertEquals(90, angles.m1,0.01);
        Assertions.assertEquals(180,   angles.b2,0.01);
    }

    @Test //90 180 180
    void vertical(){
        Leg leg = CreateLeg();

        double legLength =
                + leg.getSettings().getTopToMiddleVerticalOffset()
                        + leg.getSettings().getMiddleLength()
                        + leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                0,
                legLength);

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(90,  angles.t0,0.01);
        Assertions.assertEquals(180, angles.m1,0.01);
        Assertions.assertEquals(180,   angles.b2,0.01);
    }

    @Test //0 180 180
    void horizontalToLeft(){
        Leg leg = CreateLeg();

        double legLength =
                + leg.getSettings().getTopToMiddleVerticalOffset()
                + leg.getSettings().getMiddleLength()
                + leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                legLength,
                0,
                leg.getSettings().getTopToMiddleLength());

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(0,  angles.t0,0.01);
        Assertions.assertEquals(180, angles.m1,0.01);
        Assertions.assertEquals(180,   angles.b2,0.01);
    }

    @Test //90 180 0
    void legOnElbowsVertivalPosition(){
        Leg leg = CreateLeg();

        double legHeight = leg.getSettings().getTopToMiddleVerticalOffset()
                + leg.getSettings().getMiddleLength()
                - leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                0,
                -legHeight);

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(90,  angles.t0,0.01);
        Assertions.assertEquals(180, angles.m1,0.01);
        Assertions.assertEquals(0,   angles.b2,0.01);
    }

    @Test //90 0 0
    void legOnElbowsVertivalUpPosition(){
        Leg leg = CreateLeg();

        double legHeight = -leg.getSettings().getTopToMiddleVerticalOffset()
                + leg.getSettings().getMiddleLength()
                - leg.getSettings().getBottomLength();

        DimensionPoint point = new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                0,
                legHeight);

        LegAngles angles = leg.toAngles(point);
        Assertions.assertEquals(90,  angles.t0,0.01);
        Assertions.assertEquals(0, angles.m1,0.01);
        Assertions.assertEquals(0,   angles.b2,0.01);
    }

    @Test
    void pointXEqualsTopLength_t0Equal90(){
        Leg leg = CreateLeg();
        LegAngles angles = leg.toAngles(new DimensionPoint(
                leg.getSettings().getTopToMiddleLength(),
                10,
                - 100));
        Assertions.assertEquals(90, angles.t0,0.01);
    }
    @Test
    void pointZEqualsTopLength_t0Equal0(){
        Leg leg = CreateLeg();
        LegAngles angles = leg.toAngles(new DimensionPoint(
                100,
                10,
                leg.getSettings().getTopToMiddleLength()));
        Assertions.assertEquals(0, angles.t0,0.01);
    }
    @Test
    void Kinematik_toPointAndBack_t0IsCorrect() {
        final int averagePosition = 4000;
        LegServoPositions result = toKinematikPointAndBack(averagePosition);
        Assertions.assertEquals(averagePosition,result.t0);
    }
    @Test
    void Kinematik_toPointAndBack_m1IsCorrect() {
        final int averagePosition = 4000;
        LegServoPositions result = toKinematikPointAndBack(averagePosition);
        Assertions.assertEquals(averagePosition,result.m1);
    }
    @Test
    void Kinematik_toPointAndBack_b2IsCorrect() {
        final int averagePosition = 4000;
        LegServoPositions result = toKinematikPointAndBack(averagePosition);
        Assertions.assertEquals(averagePosition,result.b2);
    }

    LegServoPositions toKinematikPointAndBack(int averagePosition) {
        LegServoPositions somePosition =
                new LegServoPositions(averagePosition,averagePosition,averagePosition);

        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(somePosition);
        return leg.toPositions(point);
    }
}
