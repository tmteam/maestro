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
}
