import Cross.*;
import Kinematic.DimensionPoint;
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
    private SideCrossModel sideCross;
    private FrontCrossModel frontCross;
    private DimensionPoint targetPoint = new DimensionPoint(0,0,0);

    public LegGuiModel(Leg leg, MaestroSettings maestroSettings) {
        this.leg = leg;
        this.maestroSettings = maestroSettings;
        sideCross = new SideCrossModel(leg);

        frontCross =  new FrontCrossModel(leg);

        setTarget(targetPoint);
        sideCross.addXYListener(new XYListener() {
            @Override
            public void targetXYUpdated(ICrossModel sender, double x, double y) {
                setTarget(new DimensionPoint(targetPoint.x,x,y));
            }
        });

        frontCross.addXYListener(new XYListener() {
            @Override
            public void targetXYUpdated(ICrossModel sender, double x, double y) {
                setTarget(new DimensionPoint(x,targetPoint.y,y));
            }
        });

    }

    public ICrossModel getSideCrossModel(){

        return sideCross;
    }

    public ICrossModel getFrontCrossModel(){

        return frontCross;
    }

    void setTarget(DimensionPoint point){
        targetPoint = point;

        if(sideCross.getTargetX()!= point.y || sideCross.getTargetY()!= point.z)
            sideCross.setTarget(point.y,point.z);

        if(frontCross.getTargetX()!= point.x || frontCross.getTargetY()!= point.z)
            frontCross.setTarget(point.x,point.z);
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
