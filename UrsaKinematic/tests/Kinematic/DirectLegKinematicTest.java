package Kinematic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Su on 16/03/17.
 */
public class DirectLegKinematicTest extends LegTestBase {
    @Test
    void b2MoreThat180_yIsNegative(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                90, 180, 185));
        Assertions.assertTrue(point.y<0,"point.y should be negative but equals "+ point.y);
    }
    @Test
    void b2LessThat180_yIsPositive(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                90, 180, 175));
        Assertions.assertTrue(point.y>0,"point.y should be positive but equals "+ point.y);
    }
    @Test
    void t0IsNegative_m1Andb1Equal180_pointYEquals0(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                -20, 180, 180));
        Assertions.assertEquals(0,point.y,0.01);
    }
    @Test
    void t0MoreThan270_m1Andb1Equal180_pointYEquals0(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                280, 180, 180));
        Assertions.assertEquals(0,point.y,0.01);
    }
    @Test
    void t0MoreThan180_m1Andb1Equal180_pointYEquals0(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                190, 180, 180));
        Assertions.assertEquals(0,point.y,0.01);
    }
    @Test
    void t0MoreThan90_m1Andb1Equal180_pointYEquals0(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                130, 180, 180));
        Assertions.assertEquals(0,point.y,0.01);
    }

    @Test
    void m1Andb1Equal180_pointYEquals0(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(
                35, 180, 180));
        Assertions.assertEquals(0,point.y,0.01);
    }

    @Test
    void legInVerticalUpPosition_pointZEqualsVerticalLegLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(90, 0,180));

        double legHeight =
                - leg.getSettings().getTopToMiddleVerticalOffset()
                        + leg.getSettings().getMiddleLength()
                        + leg.getSettings().getBottomLength();

        Assertions.assertEquals(legHeight, point.z,0.01);
    }

    @Test
    void legOnElbowsVertivalPosition_pointZEqualsSumm(){
        Leg leg = CreateLeg();
        //Ставим ногу вертикально "на локоть", поднимая нижнюю часть вертикально вверх
        DimensionPoint point = leg.toPoint(new LegAngles(90,180,0));

        double legHeight = leg.getSettings().getTopToMiddleVerticalOffset()
                + leg.getSettings().getMiddleLength()
                - leg.getSettings().getBottomLength();

        Assertions.assertEquals(-legHeight, point.z,0.01);
    }

    @Test
    void legInVerticalPosition_pointZEqualsNegativeVerticalLegLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(leg.getVerticalPositionAngels());

        double legHeight = leg.getSettings().getTopToMiddleVerticalOffset()
                + leg.getSettings().getMiddleLength()
                + leg.getSettings().getBottomLength();

        Assertions.assertEquals(-legHeight, point.z);
    }
    @Test
    void t0Equal90_pointXEqualsTopLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(90, 12, 24));
        Assertions.assertEquals(leg.getSettings().getTopToMiddleLength(), point.x,0.01);
    }

    @Test
    void t090_m90_b90_pointZEqualsBottomLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(90, 90, 90));

        Assertions.assertEquals(
                -leg.getSettings().getBottomLength() - leg.getSettings().getTopToMiddleVerticalOffset(),
                point.z,
                0.01);
    }
    @Test
    void legIsHorisontal_pointYEqualsLegLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(90, 90, 180));

        double legLenght =
                + leg.getSettings().getMiddleLength()
                        + leg.getSettings().getBottomLength();

        Assertions.assertEquals(-legLenght, point.y,0.01);
    }

    @Test
    void t0Equal0_pointZEqualsTopLength(){
        Leg leg = CreateLeg();
        DimensionPoint point = leg.toPoint(new LegAngles(0, 12, 24));
        Assertions.assertEquals(leg.getSettings().getTopToMiddleLength(), point.z,0.01);
    }
}
