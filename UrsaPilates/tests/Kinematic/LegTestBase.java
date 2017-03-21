package Kinematic;

import Settings.LegSettings;
import Settings.ServoSettings;
import com.tmteam.jamaestro.settings.ChannelSettings;

/**
 * Created by Su on 16/03/17.
 */
public class LegTestBase {
    protected Leg CreateLeg(){

        LegSettings legSettings = new LegSettings(
                new ServoSettings(0, 10,100),
                new ServoSettings(1, -5,170),
                new ServoSettings(2, 30,70),
                10,
                25,
                50,
                70
        );
        return new Leg(legSettings,
                ChannelSettings.CreateForTest(2000,8000),
                ChannelSettings.CreateForTest(2500,7000),
                ChannelSettings.CreateForTest(3300,9000));

    }
}
