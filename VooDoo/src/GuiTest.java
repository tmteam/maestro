
import Cross.CrossModelMock;
import Cross.CrossPanelController;
import Cross.CrossPanelView;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;


public class GuiTest
{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CrossPanelView view = new CrossPanelView();
        f.add(view);
        f.pack();
        f.setSize(view.getPreferredSize());
        f.setVisible(true);



        CrossModelMock model = new CrossModelMock();
        model.SetCurrent(50,30);

        CrossPanelController controller = new CrossPanelController(model);
        controller.SetView(view);

        view.SetModel(model);
    }
}



