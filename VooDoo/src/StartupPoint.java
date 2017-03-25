import Kinematic.Body;
import Settings.BodyKinematicSettings;
import Settings.Serializer;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Su on 24/03/17.
 */
public class StartupPoint {
        /**
         28
         * Launch the application.
         29
         */
        public static void main(String[] args) throws IOException {

            BodyKinematicSettings bodySettings = Serializer.readSettings("bodyKinematic.json");
            Body body = new Body(bodySettings);

            LegGuiModel model = new LegGuiModel(body.getFrontRightLeg().getLeg(), bodySettings.getMaestro());

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