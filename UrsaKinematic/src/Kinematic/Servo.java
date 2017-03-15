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

    public Servo(ServoSettings servoSettings, ChannelSettings channelSettings){
        this.servoSettings = servoSettings;
        this.channelSettings = channelSettings;
    }

    public int toPosition(double angle){
        throw new NotImplementedException();
    }

    public  double toAngle(double servoPosition){
        throw  new NotImplementedException();
    }

    public ServoSettings getServoSettings() {
        return servoSettings;
    }

    public ChannelSettings getChannelSettings() {
        return channelSettings;
    }
}
