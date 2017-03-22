package Kinematic;

/**
 * Created by Su on 16/03/17.
 */
public class K {

    public static double NormalizeAngle(double angle){
        if(angle>360)
        {
            while (angle>360)
                angle-=360;
            return angle;
        }

        while (angle <0)
                angle+=360;
        return angle;
    }

    public static double asin(double relation){
        return Math.toDegrees(Math.asin(relation));
    }

    public static double acos(double relation){
        return Math.toDegrees(Math.acos(relation));
    }

    public static double atan(double relation){
        return Math.toDegrees(Math.atan(relation));
    }

    public static double atan2(double x, double y) {
        return Math.toDegrees(Math.atan2(y, x));
    }


    public static double sin(double angle){
        return Math.sin(Math.toRadians(angle));
    }

    public static double cos(double angle){
        return Math.cos(Math.toRadians(angle));
    }

    public static double getLength(double x, double y){
        return  Math.sqrt(x*x+ y*y);
    }

    public static LinearKoeffs createKoeffs(double x1, double x2, double y1, double y2){
        //k*x1+b = y1
        //k*x2+b = y2
        //
        //kx1 - kx2 = y1-y2
        //k = (y1-y2)/(x1-x2)
        //b = y1-kx1

        double k = (y1-y2)/(x1-x2);
        return new LinearKoeffs(k, y1-k*x1);
    }



    public static double getAngle(double lengthAgainst, double neighourALength, double neightbourBLength){

        if(lengthAgainst==0)
            return 0;

        double val = (neighourALength  *neighourALength
                    + neightbourBLength*neightbourBLength
                    - lengthAgainst    *lengthAgainst)
                /(2*neightbourBLength*neighourALength);

        if(val>1 && val<1.01)
            val =1;
        else if(val<-1 && val>-1.01)
            val =-1;

        return Math.toDegrees(Math.acos((val)));
    }

    public static InverseKinematicResult getAnglesFor(
            double x,
            double y,
            double l1,
            double l2)
    {
        return getAnglesFor(x,y,l1,l2,true);
    }

    public static InverseKinematicResult getAnglesFor(
            double x,
            double y,
            double l1,
            double l2,
            boolean bendAngleIsLessThan180
            ){
        //http://roboty6.narod.ru/inverseKinematics.htm

        double radiusVector = getLength(x, y);

        double originBendAngle =  getAngle(radiusVector, l1, l2);//the originBendAngle will be always less than 180

        double bendAngle = originBendAngle;

        if(!bendAngleIsLessThan180)
            bendAngle = 360-bendAngle;


        double q1 = K.atan2(x,y);

        double q2 =  getAngle(l2,l1,radiusVector);

        return new InverseKinematicResult( bendAngleIsLessThan180?(q1+q2):(q1-q2), bendAngle);
    }
}

