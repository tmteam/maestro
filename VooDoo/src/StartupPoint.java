import javax.swing.*;
import java.awt.*;

/**
 * Created by Su on 24/03/17.
 */
public class StartupPoint {
        /**
         28
         * Launch the application.
         29
         */
        public static void main(String[] args) {

            LegGuiModel model = new LegGuiModel();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        LegGui windowContent = new LegGui();
                        windowContent.createFrame().setVisible(true);

                        LegGuiPresenter presenter = new LegGuiPresenter(windowContent,model);
                        presenter.Run();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    private static void createAndShowGUI() {
        LegGui mainWindow = new LegGui();
        JFrame f = new JFrame("Swing Paint Demo");


    }
}