import Cross.CrossModelMock;
import Cross.ICrossModel;
import Kinematic.Leg;
import Settings.LegSettings;
import com.tmteam.jamaestro.MaestroServoController;
import com.tmteam.jamaestro.api.Product;
import com.tmteam.jamaestro.binding.BindingListener;
import com.tmteam.jamaestro.binding.DriverBinding;
import com.tmteam.jamaestro.binding.LibUsbDriverBinding;
import com.tmteam.jamaestro.settings.MaestroSettings;

/**
 * Created by Su on 24/03/17.
 */
public class LegGuiModel {

    private ILog log;
    private Leg leg;
    private MaestroSettings maestroSettings;
    private MaestroServoController controller;

    public LegGuiModel(Leg leg, MaestroSettings maestroSettings) {
        this.leg = leg;
        this.maestroSettings = maestroSettings;
    }

    public ICrossModel getSideCrossModel(){
        return new CrossModelMock();
    }

    public ICrossModel getFrontCrossModel(){
        return new CrossModelMock();
    }

    public boolean isConnected(){
        return  false;
    }
    public void connect(){
        log.writeMessage("connection start");

        BindingListener listener = new BindingListener();

        LibUsbDriverBinding.bindToDevice(Product.MINI24, listener);
        DriverBinding driver = listener.getBindingOrNull();
        if(driver==null){
            log.writeError("No driver found");
            return;
        }
        else
        {
            log.writeMessage("Driver connected");
        }
        controller = new MaestroServoController(driver, maestroSettings);
    }

    public ILog getLog() {
        return log;
    }

    public void setLog(ILog log) {
        this.log = log;
        log.writeMessage("log setted");

    }
}
