import Cross.CrossPanelPresenter;
import Cross.ICrossModel;

/**
 * Created by Su on 24/03/17.
 */
public class LegGuiPresenter {
    private UiLog log;
    private final LegGui view;
    private final LegGuiModel model;
    private CrossPanelPresenter sideCrossPresenter;
    private CrossPanelPresenter frontCrossPresenter;

    public LegGuiPresenter(LegGui view, LegGuiModel model){

        this.view = view;
        this.model = model;
    }

    public void Run() {
        log = new UiLog();
        model.setLog(log);
        model.connect();

        log.writeMessage("Side controll initialization");
        ICrossModel sideCrossModel =  this.model.getSideCrossModel();
        sideCrossPresenter = new CrossPanelPresenter(sideCrossModel,view.getSideCrossView());

        log.writeMessage("Front controll initialization");
        ICrossModel frontCrossModel =  this.model.getFrontCrossModel();
        frontCrossPresenter = new CrossPanelPresenter(frontCrossModel,view.getFrontCrossView());

        log.writeMessage("Ready to vudu...");
    }
}
