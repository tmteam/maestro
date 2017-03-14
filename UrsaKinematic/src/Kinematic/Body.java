package Kinematic;

import Settings.BodyKinematicSettings;
import Settings.LegBindingSettings;
import Settings.LegSettings;
import com.tmteam.jamaestro.settings.Settings;

/**
 * Created by Su on 15/03/17.
 */
public class Body {

    public Body(BodyKinematicSettings settings){
        this.settings = settings;
        LegBindingSettings frontLeftLegSettings = settings.getFrontLeftLeg();
        frontLeftLeg =  createLegBinding(settings.getFrontLeftLeg());
        frontRightLeg =  createLegBinding(settings.getFrontRightLeg());
        backLeftLeg =  createLegBinding(settings.getBackLeftLeg());
        backRightLeg =  createLegBinding(settings.getBackRightLeg());
    }

    private LegBinding createLegBinding(LegBindingSettings legBindingSettings){
        LegSettings legSettings = legBindingSettings.getLegSettings();

        int channel0 = legSettings.getTop_0().getChannel();
        int channel1 = legSettings.getMiddle_1().getChannel();
        int channel2 = legSettings.getBottom_2().getChannel();

        Settings maestro = settings.getMaestro();
        return  new LegBinding(legBindingSettings,
                maestro.getChannel(channel0),
                maestro.getChannel(channel1),
                maestro.getChannel(channel2));

    }

    private LegBinding frontLeftLeg;
    private LegBinding frontRightLeg;
    private LegBinding backLeftLeg;
    private LegBinding backRightLeg;
    private BodyKinematicSettings settings;

    public LegBinding getFrontLeftLeg() {
        return frontLeftLeg;
    }

    public LegBinding getFrontRightLeg() {
        return frontRightLeg;
    }

    public LegBinding getBackLeftLeg() {
        return backLeftLeg;
    }

    public LegBinding getBackRightLeg() {
        return backRightLeg;
    }
}
