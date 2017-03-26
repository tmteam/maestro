import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Su on 25/03/17.
 */
public class UILog implements ILog {
    private LegGui gui;
    private String buffer = "Log start";

    public void SetUi(LegGui gui){
        this.gui = gui;
        gui.appendLogMessage(buffer);
    }
    public UILog() {
    }

    @Override
    public void writeMessage(String message) {
        write(getCurrentTimestamp()+" "+message);
    }

    @Override
    public void writeWarning(String message) {
        write("[warning] "+getCurrentTimestamp()+" "+message);

    }

    @Override
    public void writeError(String message) {
        write("[ERROR] "+getCurrentTimestamp()+" "+message);
    }
    private void write(String message) {
        buffer += "\r\n" + message;
        if (gui != null) {
            gui.appendLogMessage("\r\n" + message);
        }
    }
    String getCurrentTimestamp(){
        return new SimpleDateFormat("mm:ss.SSS").format(new Date());
    }
}
