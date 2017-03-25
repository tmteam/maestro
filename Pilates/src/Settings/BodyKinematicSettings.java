package Settings;


import com.tmteam.jamaestro.settings.ChannelSettings;
import com.tmteam.jamaestro.settings.MaestroSettings;

/**
 * Created by Su on 14/03/17.
 */
public class BodyKinematicSettings {

    public BodyKinematicSettings(){
        maestro = new MaestroSettings();

        for(int i = 0; i<12; i++){
            maestro.AddChannel(i, new ChannelSettings());
        }

        frontLeftLeg = new LegBindingSettings();
        frontRightLeg = new LegBindingSettings();
        backLeftLeg = new LegBindingSettings();
        backRightLeg = new LegBindingSettings();
    }

    private com.tmteam.jamaestro.settings.MaestroSettings maestro;

    private LegBindingSettings frontRightLeg;
    private LegBindingSettings backRightLeg;
    private LegBindingSettings backLeftLeg;
    private LegBindingSettings frontLeftLeg;

    public LegBindingSettings getFrontLeftLeg() {
        return frontLeftLeg;
    }

    public LegBindingSettings getFrontRightLeg() {
        return frontRightLeg;
    }

    public LegBindingSettings getBackLeftLeg() {
        return backLeftLeg;
    }

    public LegBindingSettings getBackRightLeg() {
        return backRightLeg;
    }

    public MaestroSettings getMaestro() {
        return maestro;
    }
}
