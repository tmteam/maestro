package Kinematic;

import Settings.ServoSettings;
import com.tmteam.jamaestro.settings.ChannelSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Su on 14/03/17.
 */
public class Servo {

    private ServoSettings servoSettings;
    private ChannelSettings channelSettings;

    private LinearKoeffs angle2Pos;
    private LinearKoeffs pos2Angle;

    public Servo(ServoSettings servoSettings, ChannelSettings channelSettings){
        this.servoSettings = servoSettings;
        this.channelSettings = channelSettings;

        angle2Pos = K.createKoeffs(
                servoSettings.getMinValue(),
                servoSettings.getMaxValue(),
                channelSettings.getMinimum(),
                channelSettings.getMaximum()
        );

        pos2Angle = K.createKoeffs(
                channelSettings.getMinimum(),
                channelSettings.getMaximum(),
                servoSettings.getMinValue(),
                servoSettings.getMaxValue()
        );
    }

    public int toPosition(double angle){
        return (int)Math.round(angle2Pos.calcFor(angle));
    }

    public  double toAngle(double servoPosition){
        return pos2Angle.calcFor(servoPosition);
    }

    public ServoSettings getServoSettings() {
        return servoSettings;
    }

    public ChannelSettings getChannelSettings() {
        return channelSettings;
    }
}
