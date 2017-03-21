package Kinematic;

import Settings.LegSettings;
import com.tmteam.jamaestro.settings.ChannelSettings;

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

    public LegAngles getVerticalPositionAngels(){
       return new LegAngles(90,
                180, 180);
    }

    public LegServoPositions toPositions(DimensionPoint point){
        return toPositions(toAngles(point));
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

    public DimensionPoint toPoint(LegServoPositions positions){
        return toPoint(toAngles(positions));
    }

    public DimensionPoint toPoint(LegAngles angles){
        //Зафиксируем ногу вертикально и посмотрим с боку:
        //угол 0: это вертикально стоящая нога вверх
        //Расстояние по глобальному Y:
        double legFinalY =
                -settings.getMiddleLength()*K.sin(angles.m1)
                -settings.getBottomLength()*K.sin(angles.b2-angles.m1);
        //Расстояние по глобальному Z:
        double heightOffset =
                -settings.getMiddleLength()*K.cos(angles.m1)
                +settings.getBottomLength()*K.cos(angles.b2-angles.m1)
                +settings.getTopToMiddleVerticalOffset();

        //Посмотрим на эту ногу спереди
        //длинна отрезка от крепления ноги до её пятки:
        double frontLength = K.getLength(heightOffset,settings.getTopToMiddleLength());

        //Угол от крепления ноги до её пятки (относительно глобальной вертикали
        double frontAngle = K.acos(settings.getTopToMiddleLength()/frontLength)+ angles.t0;

        if(heightOffset<0)
            frontAngle -=180;

        return new DimensionPoint(
                K.sin(frontAngle)* frontLength,
                legFinalY,//в не зависимости от поворота t0, данное расстояние сохранится. Но в системе координат ноги оно будет Y
                K.cos(frontAngle)* frontLength
        );
    }

    public LegAngles toAngles(DimensionPoint point){
        //w*w + y*y = r*r
        //y = sqrt(rr-ww)
        //y = o + tar
        //tar = sqrt(rr-ww)-o

        double r = K.getLength(point.x  , point.z);
        double w = settings.getTopToMiddleLength();
        //итоговая длинна плеча
        double topToMiddleConverted = K.getLength(w, settings.getTopToMiddleVerticalOffset());

        double leg12Y = (r>topToMiddleConverted?1:-1)* Math.sqrt(r*r - w*w)-settings.getTopToMiddleVerticalOffset();
        double leg12X = point.y;

        //расстояние от верхней точки до пятки, по пифагору:
        InverseKinematicResult leg12Angles = getLeg12Angles(leg12X, leg12Y);


        //Угол, образуемый фронтальным радус вектором
        double totalAngle = K.acos(point.z/r);

        // фронтальный угол радус вектором и нижней leg12
        double topToMiddleConvertedAngle = K.getAngle(
                topToMiddleConverted,
                r,
                Math.abs(leg12Y));

        double t0Angle = totalAngle+ (leg12Y>0
                ? (topToMiddleConvertedAngle-90)
                : (90-topToMiddleConvertedAngle));

        return new LegAngles(t0Angle, leg12Angles.baseAngle, leg12Angles.bendAngle);
    }

    /**
     * Считает углы m1 и b2 для известных X и Y
     * в координатах ноги без плеча
     *
     * XY: если смотреть на вертикально (t0 = 90) поставленную ногу.
     * Y>0 нога сверху
     * X>0 нога спереди (в сторону головы)
     */
    private InverseKinematicResult getLeg12Angles(double leg12X, double leg12Y) {
       return   K.getAnglesFor(leg12X, leg12Y, settings.getMiddleLength(), settings.getBottomLength());
    }
}
