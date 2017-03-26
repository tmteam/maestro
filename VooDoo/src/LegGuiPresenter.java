import Cross.CrossPanelPresenter;
import Cross.ICrossModel;

/**
 * Created by Su on 24/03/17.
 */
public class LegGuiPresenter {
    private UILog log;
    private final LegGui view;
    private final LegGuiModel model;

    private CrossPanelPresenter topCrossPresenter;
    private CrossPanelPresenter sideCrossPresenter;
    private CrossPanelPresenter frontCrossPresenter;

    public LegGuiPresenter(LegGui view, LegGuiModel model){

        this.view = view;
        this.model = model;
    }

    public void Run() {
        createLog();

        model.setLog(log);
        model.connect();

        log.writeMessage("Side control initialization");
        ICrossModel sideCrossModel =  this.model.getSideCrossModel();
        sideCrossPresenter = new CrossPanelPresenter(sideCrossModel,view.getSideCrossView());

        log.writeMessage("Front control initialization");
        ICrossModel frontCrossModel =  this.model.getFrontCrossModel();
        frontCrossPresenter = new CrossPanelPresenter(frontCrossModel,view.getFrontCrossView());

        log.writeMessage("Top control initialization");
        ICrossModel topCrossModel = this.model.getTopCrossModel();
        topCrossPresenter = new CrossPanelPresenter(topCrossModel, view.getTopCrossView());
        log.writeMessage("Ready to vudu");
    }

    private void createLog() {
        log = new UILog();
        log.writeMessage("log initialized");
        log.SetUi(view);
    }
}
