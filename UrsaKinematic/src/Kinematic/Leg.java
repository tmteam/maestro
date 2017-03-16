package Kinematic;

import Settings.LegSettings;
import com.tmteam.jamaestro.settings.ChannelSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Su on 14/03/17.
 */
public class Leg {
    private LegSettings settings;

    private Servo servoT0;
    private Servo servoM1;
    private Servo servoB2;

    public Leg(LegSettings legSettings,
               ChannelSettings t0Channel,
               ChannelSettings m1Channel,
               ChannelSettings b2Channel){

        this.settings = legSettings;
        servoT0 = new Servo(legSettings.getTop_0(), t0Channel);
        servoM1 = new Servo(legSettings.getMiddle_1(), m1Channel);
        servoB2 = new Servo(legSettings.getBottom_2(), b2Channel);
    }

    public Servo getServoT0() {
        return servoT0;
    }

    public Servo getServoM1() {
        return servoM1;
    }

    public Servo getServoB2() {
        return servoB2;
    }

    public LegSettings getSettings() {
        return settings;
    }

    public LegServoPositions toPositions(DimentionPoint point){
        return toPositions(toAngels(point));
    }

    public LegServoPositions toPositions(LegAngles angles){
        return new LegServoPositions(
                servoT0.toPosition(angles.t0),
                servoM1.toPosition(angles.m1),
                servoB2.toPosition(angles.b2)
        );
    }

    public LegAngles toAngles(LegServoPositions positions){
        return new LegAngles(
                servoT0.toAngle(positions.t0),
                servoM1.toAngle(positions.m1),
                servoB2.toAngle(positions.b2)
        );
    }

    public DimentionPoint toPoint(LegServoPositions positions){
        return toPoint(toAngles(positions));
    }

    public DimentionPoint toPoint(LegAngles angles){
        //Зафиксируем ногу вертикально и посмотрим с боку:
        //угол 0: это вертикально стоящая нога вверх
        //Расстояние по глобальному Y:
        double legFinalY =
                +settings.getMiddleLength()*Math.sin(Math.toRadians(angles.m1))
                +settings.getBottomLength()*Math.sin(Math.toRadians(angles.b2-angles.m1));
        //Расстояние по глобальному Z:
        double heightOffset =
                +settings.getMiddleLength()*Math.cos(Math.toRadians(angles.m1))
                +settings.getBottomLength()*Math.cos(Math.toRadians(angles.b2-angles.m1))
                +settings.getTopToMiddleVerticalOffset();

        //Посмотрим на эту ногу спереди
        //длинна отрезка от крепления ноги до её пятки:
        double frontLength = Math.sqrt(heightOffset*heightOffset + Math.pow(settings.getTopToMiddleLength(),2));

        //Угол от крепления ноги до её пятки (относительно глобальной вертикали
        double frontAngle = Math.asin(settings.getTopToMiddleLength()/frontLength)+ angles.t0;

        return new DimentionPoint(
                Math.sin(Math.toRadians(frontAngle))* frontLength,
                legFinalY,//в не зависимости от поворота t0, данное расстояние сохранится. Но в системе координат ноги оно будет Y
                Math.cos(Math.toRadians(frontAngle))* frontLength
        );
    }

    public LegAngles toAngels(DimentionPoint point){
        //w*w + y*y = r*r
        //y = sqrt(rr-ww)
        //y = o + tar
        //tar = sqrt(rr-ww)-o

        double r = K.getLength(point.x  , point.z);
        double w = settings.getTopToMiddleLength();
        double leg12Y = Math.sqrt(r*r - w*w)-settings.getTopToMiddleVerticalOffset();
        double leg12X = point.y;

        //расстояние от верхней точки до пятки, по пифагору:
        LegAngles leg12Angles = getLeg12Angles(leg12X, leg12Y);


        //Угол, образуемый фронтальным радус вектором
        double totalAngle = 180 - Math.toDegrees(Math.acos(point.z/r));

        //итоговая длинна плеча
        double topToMiddleConverted = K.getLength(w, settings.getTopToMiddleVerticalOffset());
        // фронтальный угол радус вектором и нижней leg12
        double topToMiddleConvertedAngle = K.getAngle(topToMiddleConverted,
                r,
                leg12Y);

        double t0Angle = totalAngle+ topToMiddleConvertedAngle - 90;

        return new LegAngles(t0Angle, leg12Angles.m1, leg12Angles.b2);
    }

    /**
     * Считает углы m1 и b2 для известных X и Y
     * в координатах ноги без плеча
     *
     */
    private LegAngles getLeg12Angles(double leg12X, double leg12Y) {
        //http://roboty6.narod.ru/inverseKinematics.htm

        //возьмём ногу без топ сервы (leg12) и посмотрим на неё с боку

        double leg12Length = K.getLength(leg12X, leg12Y);
        //теперь, имея все три стороны - считаем углы:
        double b2angle =  K.getAngle(
                leg12Length,
                settings.getBottomLength(),
                settings.getMiddleLength());

        double q2 = K.getAngle(
                settings.getMiddleLength(),
                settings.getBottomLength(),
                leg12Length);

        double q1 = Math.toDegrees(Math.atan(leg12Y/leg12X));

        return new LegAngles(0, q1+q2, b2angle);
    }
}
