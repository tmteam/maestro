package Kinematic;

/**
 * Created by Su on 16/03/17.
 */
public class K {

    public static double getLength(double x, double y){
        return  Math.sqrt(x*x+ y*y);
    }

    public static double getAngle(double lengthAgainst, double neighourALength, double neightbourBLength){

        if(lengthAgainst==0)
            return 0;

        double val = (neighourALength*neighourALength + neightbourBLength*neightbourBLength - lengthAgainst*lengthAgainst)
                /(2*neightbourBLength*neighourALength);

        return Math.toDegrees(Math.acos((val)));


    }

}
