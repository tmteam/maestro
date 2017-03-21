package Kinematic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Su on 21/03/17.
 */
public class KGetAnglesTest {
    private final double l1 = 150;
    private final double l2 = 100;
    //fold
    @Test
    void getAngles_FoldToDown_baseEquals270(){

        AssertBaseAngle(0,-l1+l2,270);
    }
    @Test
    void getAngles_FoldToDown_bendEquals0(){
        AssertBendAngle(0,-l1+l2,0);
    }

    @Test
    void getAngles_FoldToUp_baseEquals90(){
        AssertBaseAngle(0,l1-l2,90);
    }
    @Test
    void getAngles_FoldToUp_bendEquals0(){
        AssertBendAngle(0,l1-l2,0);
    }

    @Test
    void getAngles_FoldToRight_baseEquals0(){
        AssertBaseAngle(l1-l2,0,0);
    }
    @Test
    void getAngles_FoldToRight_bendEquals0(){
        AssertBendAngle(l1-l2,0,0);
    }

    @Test
    void getAngles_FoldToLeft_baseEquals180(){
        AssertBaseAngle(-l1+l2,0,180);
    }
    @Test
    void getAngles_FoldToLeft_bendEquals0(){
        AssertBendAngle(-l1+l2,0,0);
    }

    //Straight
    @Test
    void getAngles_LineToDown_baseEquals270(){
        AssertBaseAngle(0,-l1-l2,270);
    }
    @Test
    void getAngles_LineToDown_bendEquals180(){
        AssertBendAngle(l1+l2,0,180);
    }

    @Test
    void getAngles_LineToUp_baseEquals90(){
        AssertBaseAngle(0,l1+l2,90);
    }
    @Test
    void getAngles_LineToUp_bendEquals180(){
        AssertBendAngle(l1+l2,0,180);
    }

    @Test
    void getAngles_LineToRight_baseEquals0(){
        AssertBaseAngle(l1+l2,0,0);
    }
    @Test
    void getAngles_LineToRight_bendEquals180(){
        AssertBendAngle(l1+l2,0,180);
    }

    @Test
    void getAngles_LineToLeft_baseEquals180(){
        AssertBaseAngle(-(l1+l2),0,180);
    }
    @Test
    void getAngles_LineToLeft_bendEquals180(){
       AssertBendAngle(-(l1+l2),0,180);
    }

    //l2 then l1
    @Test // bend i- +
    void getAngles_VerticalUpLeftStraight_bendEquals270(){
        AssertBendAngle(-l2,+l1,270);
    }
    @Test // base i- +
    void getAngles_VerticalUpleftStraight_baseEquals90(){
        AssertBaseAngle(-l2,+l1,90,false);
    }

    @Test // bend i- -
    void getAngles_VerticalDownLeftStraight_bendEquals90(){
        AssertBendAngle(-l2,-l1,90);
    }
    @Test // base i- -
    void getAngles_VerticalDownleft_baseEquals270(){
        AssertBaseAngle(-l2,-l1,270);
    }

    @Test // bend i+ -
    void getAngles_VerticalDownRightStraight_bendEquals270(){
        AssertBendAngle(l2,-l1,270);
    }
    @Test // base i+ -
    void getAngles_VerticalDownRightStraight_baseEquals270(){
        AssertBaseAngle(l2,-l1,270,false);
    }

    @Test // bend i+ +
    void getAngles_VerticalStraight_bendEquals90(){
        AssertBendAngle(l2,l1,90);
    }
    @Test // base i+ +
    void getAngles_VerticalStraight_baseEquals90(){
        AssertBaseAngle(l2,l1,90);
    }

    // l1 then l2
    @Test // base - -
    void getAngles_VerticalStraightLeft_baseEquals180(){
        AssertBaseAngle(-l1,-l2,180,false);
    }
    @Test //bend - -
    void getAngles_VerticalStraightLeft_bendEquals270(){
        AssertBendAngle(-l1,-l2,270);
    }

    @Test // base + -
    void getAngles_HorizontalStraightDown_baseEquals0(){
        AssertBaseAngle(l1,-l2,0);
    }
    @Test //bend + -
    void getAngles_HorizontalStraight_bendEquals90(){
        AssertBendAngle(l1,-l2,90);
    }


    @Test // base + +
    void getAngles_HorizontalStraight_baseEquals0(){
        AssertBaseAngle(l1,l2,0,false);
    }
    @Test //bend + +
    void getAngles_HorizontalStraight_bendEquals270(){
        AssertBendAngle(l1,l2,270);
    }

    @Test //base - +
    void getAngles_HorizontalStraightLeft_baseEquals180(){
        AssertBaseAngle(-l1,l2, 180);
    }
    @Test //bend - +
    void getAngles_HorizontalStraightLeft_bendEquals90(){
        AssertBendAngle(-l1, l2, 90);
    }



    private void  AssertBendAngle(double x, double y, double expectedAngle){
        Assertions.assertEquals(expectedAngle,
               K.NormalizeAngle(K.getAnglesFor(x, y, l1,l2,
                       expectedAngle<=180).bendAngle),0.01);
    }
    private void  AssertBaseAngle(double x, double y, double expectedAngle) {
        AssertBaseAngle(x,y,expectedAngle,true);
    }

    private void  AssertBaseAngle(double x, double y, double expectedAngle, boolean bendAngleLessThan180){
        Assertions.assertEquals(expectedAngle,
               K.NormalizeAngle(K.getAnglesFor(x, y, l1,l2,bendAngleLessThan180).baseAngle),0.01);
    }
}
