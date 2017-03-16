package Kinematic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Su on 16/03/17.
 */
class KTest {
    @Test
    void getLength() {
        Assertions.assertEquals(5, K.getLength(3, 4));
    }

    @Test
    void getAngle() {
        Assertions.assertEquals(90,K.getAngle(5,4,3));
    }
    @Test
    void getAngle_returns0_for0Length() {
        Assertions.assertEquals(0, K.getAngle(0,12,24));
    }

    @Test
    void getAngle_returnsNaN_forImpossibleTriangle() {
        double a = 12;
        double b = 24;
        double c = 42;

        Assertions.assertTrue(Double.isNaN(K.getAngle(a,b,c)));
    }

    @Test
    void getAngle_anglesSummAre180() {
        double a = 21;
        double b = 24;
        double c = 42;

        double aAngle =  K.getAngle(a,b,c);
        double bAngle =  K.getAngle(b,a,c);
        double cAngle =  K.getAngle(c,b,a);

        Assertions.assertEquals(180, aAngle+ bAngle+ cAngle, 0.01);
    }
}