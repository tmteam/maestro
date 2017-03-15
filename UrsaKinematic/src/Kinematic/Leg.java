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


    public LegServoPositions toPositions(DimentionPoint point){
        throw new NotImplementedException();
    }
    public LegServoPositions toPostions(LegAngles angles){
        return new LegServoPositions(
                servoT0.toPosition(angles.t0),
                servoM1.toPosition(angles.m1),
                servoB2.toPosition(angles.b2)
        );
    }
    public LegAngles toAngels(DimentionPoint point){
        throw new NotImplementedException();
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
        double legX =
                +settings.getMiddleLength()*Math.sin(Math.toRadians(angles.m1))
                +settings.getBottomLength()*Math.sin(Math.toRadians(angles.b2-angles.m1));
        double legY =
                +settings.getMiddleLength()*Math.cos(Math.toRadians(angles.m1))
                +settings.getBottomLength()*Math.cos(Math.toRadians(angles.b2-angles.m1));

        //Посмотрим на ногу спереди и рассчитаем отклонения от точки крепления до пятки
        //по высоте:
        double heightOffset = legY+ settings.getTopToMiddleVerticalOffset();
        //по ширине:
        double widthOffset = settings.getTopToMiddleLength();
        //длинна отрезка от крепления ноги до её пятки:
        double frontLength = Math.sqrt(heightOffset*heightOffset + widthOffset*widthOffset);

        //Угол от крепления ноги до её пятки (относительно глобальной вертикали
        double frontAngle = Math.asin(widthOffset/frontLength)+ angles.t0;

        return new DimentionPoint(
                Math.sin(Math.toRadians(frontAngle))* frontLength,
                legX,//в не зависимости от поворота t0, данное расстояние сохранится. Но в системе координат ноги оно будет Y
                Math.cos(Math.toRadians(frontAngle))* frontLength
        );
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
}
