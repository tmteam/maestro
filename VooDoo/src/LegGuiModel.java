import Cross.*;
import Kinematic.DimensionPoint;
import Kinematic.Leg;
import Kinematic.LegAngles;
import Kinematic.LegServoPositions;
import Settings.LegSettings;
import com.tmteam.jamaestro.MaestroServoController;
import com.tmteam.jamaestro.api.Product;
import com.tmteam.jamaestro.binding.BindingListener;
import com.tmteam.jamaestro.binding.DriverBinding;
import com.tmteam.jamaestro.binding.LibUsbDriverBinding;
import com.tmteam.jamaestro.settings.MaestroSettings;

public class LegGuiModel {
    private ILog log;
    private Leg leg;
    private MaestroSettings maestroSettings;
    private MaestroServoController controller;
    private TopCrossModel topCross;
    private SideCrossModel sideCross;
    private FrontCrossModel frontCross;
    private String previousMovMessage = "";

    private DimensionPoint targetPoint = new DimensionPoint(0,0,0);

    public LegGuiModel(Leg leg, MaestroSettings maestroSettings) {
        this.leg = leg;
        this.maestroSettings = maestroSettings;
        sideCross = new SideCrossModel(leg);

        frontCross =  new FrontCrossModel(leg);
        topCross = new TopCrossModel(leg);

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

        topCross.addXYListener(new XYListener() {
            @Override
            public void targetXYUpdated(ICrossModel sender, double x, double y) {
                setTarget(new DimensionPoint(x,y,targetPoint.z));
            }
        });

    }
    public ICrossModel getTopCrossModel(){

        return topCross;
    }

    public ICrossModel getSideCrossModel(){

        return sideCross;
    }

    public ICrossModel getFrontCrossModel(){

        return frontCross;
    }
    void setTarget(DimensionPoint point){
        //log.writeWarning("jmp "+distance+" from "+ currentPosition+" to "+ point);

        DimensionPoint oldPoint = targetPoint.copy();
        targetPoint = point;

        sideCross.setTarget(point.y,point.z);
        frontCross.setTarget(point.x,point.z);
        topCross.setTarget(point.x,point.y);
        LegAngles originAngles = leg.toAngles(targetPoint);

        LegAngles angles = correctT0Range(originAngles);
        angles = correctM1Range(angles);
        angles = correctB2Range(angles);

        DimensionPoint correctedPosition = leg.toPoint(angles);
        double distance = correctedPosition.distanceTo(point);
        if(distance> 100){
            log.writeMessage("jmp");
            return;
        }
        else{

            sideCross.setCurrent(correctedPosition.y, correctedPosition.z);
            frontCross.setCurrent(correctedPosition.x,correctedPosition.z);
            topCross.setCurrent(correctedPosition.x,  correctedPosition.y);

            LegServoPositions servoPositions = leg.toPositions(angles);
            if(log!=null) {
                DimensionPoint correctedPoint = leg.toPoint(servoPositions);
                String movMessage =  "mov " + point;

                if(!correctedPoint.IsSimilarTo(point, 0))
                    movMessage +=  "  cor: " + correctedPoint;

                if(movMessage!=previousMovMessage){
                    log.writeMessage(movMessage);
                    previousMovMessage = movMessage;
                }
            }
            if(controller!=null) {

                controller.setTarget(leg.getServoT0().getServoSettings().getChannel(), servoPositions.t0);
                controller.setTarget(leg.getServoM1().getServoSettings().getChannel(), servoPositions.m1);
                controller.setTarget(leg.getServoB2().getServoSettings().getChannel(), servoPositions.b2);
            }
        }
    }

    public boolean isConnected(){
        return  controller!=null;
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
        controller.setSpeed(leg.getServoT0().getServoSettings().getChannel(),0);
        controller.setSpeed(leg.getServoM1().getServoSettings().getChannel(),0);
        controller.setSpeed(leg.getServoB2().getServoSettings().getChannel(),0);

    }

    public ILog getLog() {
        return log;
    }

    public void setLog(ILog log) {
        this.log = log;
        log.writeMessage("log setted");

    }

    private LegAngles correctT0Range(LegAngles angles) {
        double minT0= leg.getServoT0().getServoSettings().getMinValue();
        double maxT0= leg.getServoT0().getServoSettings().getMaxValue();
        if(angles.t0<minT0)
            return new LegAngles(minT0, angles.m1, angles.b2);
        else if(angles.t0>maxT0)
            return new LegAngles(maxT0, angles.m1, angles.b2);
        else
            return angles;
    }
    private LegAngles correctM1Range(LegAngles angles) {
        double minM1= leg.getServoM1().getServoSettings().getMinValue();
        double maxM1= leg.getServoM1().getServoSettings().getMaxValue();
        if(angles.m1<minM1)
            return new LegAngles(angles.t0, minM1, angles.b2);
        else if(angles.m1>maxM1)
            return new LegAngles(angles.t0, maxM1, angles.b2);
        else
            return angles;
    }
    private LegAngles correctB2Range(LegAngles angles) {
        double minB2= leg.getServoB2().getServoSettings().getMinValue();
        double maxB2= leg.getServoB2().getServoSettings().getMaxValue();
        if(angles.b2<minB2)
            return new LegAngles(angles.t0, angles.m1, minB2);
        else if(angles.b2>maxB2)
            return new LegAngles(angles.t0, angles.m1, maxB2);
        else
            return angles;
    }

}
