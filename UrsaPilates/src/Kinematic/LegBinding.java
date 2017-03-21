package Kinematic;

import Settings.LegBindingSettings;
import com.tmteam.jamaestro.settings.ChannelSettings;

/**
 * Created by Su on 15/03/17.
 */
public class LegBinding {

    public LegBinding(LegBindingSettings settings,
                      ChannelSettings t0Channel,
                      ChannelSettings m1Channel,
                      ChannelSettings b2Channel) {
        this.leg = new Leg(settings.getLegSettings(), t0Channel,m1Channel, b2Channel);
        this.settings = settings;
    }
    private Leg leg;
    private LegBindingSettings settings;

    public Leg getLeg() {
        return leg;
    }

    public LegBindingSettings getSettings() {
        return settings;
    }
}
