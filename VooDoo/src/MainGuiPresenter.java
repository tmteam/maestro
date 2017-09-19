import Cross.CrossPanelPresenter;
import Cross.ICrossModel;

/**
 * Created by Su on 24/03/17.
 */
public class MainGuiPresenter {
    private UILog log;
    private final MainGui view;
    private final LegGuiModel model;

    private CrossPanelPresenter topCrossPresenter;
    private CrossPanelPresenter sideCrossPresenter;
    private CrossPanelPresenter frontCrossPresenter;

    public MainGuiPresenter(MainGui view, LegGuiModel model){

        this.view = view;
        this.model = model;
    }

    public void Run() {
        createLog();

        model.setLog(log);
        model.connect();

        ActivateLeg(model);
    }

    void ActivateLeg(LegGuiModel model){
        model.setLog(log);

        log.writeMessage("Side control initialization");
        ICrossModel sideCrossModel =  this.model.getSideCrossModel();
        sideCrossPresenter = new CrossPanelPresenter(sideCrossModel,view.getSideCrossView(),"Side view");

        log.writeMessage("Front control initialization");
        ICrossModel frontCrossModel =  this.model.getFrontCrossModel();
        frontCrossPresenter = new CrossPanelPresenter(frontCrossModel,view.getFrontCrossView(),"Front view");

        log.writeMessage("Top control initialization");
        ICrossModel topCrossModel = this.model.getTopCrossModel();
        topCrossPresenter = new CrossPanelPresenter(topCrossModel, view.getTopCrossView(),"Top view");
        log.writeMessage("Leg control activated");
    }

    private void createLog() {
        log = new UILog();
        log.writeMessage("log initialized");
        log.SetUi(view);
    }
}
