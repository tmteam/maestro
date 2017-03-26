import Cross.CrossModelMock;
import Cross.CrossPanelPresenter;
import Cross.CrossPanelView;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Su on 22/03/17.
 */
public class LegGui extends JFrame {
    private JPanel MainPanel;
    private JTextArea console;
    private JPanel SideViewPanel;
    private JPanel FrontViewPanel;
    private JScrollPane consoleScrollPane;

    public JPanel getMainPanel() {
        return MainPanel;
    }


    public CrossPanelView getSideCrossView() {
        return (CrossPanelView) SideViewPanel;
    }

    public CrossPanelView getFrontCrossView() {
        return (CrossPanelView) FrontViewPanel;
    }

    private void createUIComponents() {

        SideViewPanel = new CrossPanelView();

        FrontViewPanel = new CrossPanelView();

        console = new JTextArea();
        console.setEditable(false);
        console.setLineWrap(true);
    }

    public void appendLogMessage(String message) {
        console.append(message);
        console.setCaretPosition(console.getDocument().getLength());
    }

    public JFrame createFrame() {

        JFrame frame = new JFrame();
        frame.setTitle("U.R.S.A. VooDoo leg controller");
        frame.setContentPane(MainPanel);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setSize(MainPanel.getPreferredSize());
        return frame;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        MainPanel = new JPanel();
        MainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        SideViewPanel.setBackground(new Color(-6261268));
        SideViewPanel.setEnabled(true);
        MainPanel.add(SideViewPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), new Dimension(300, 300), null, 0, false));
        FrontViewPanel.setBackground(new Color(-1302483));
        MainPanel.add(FrontViewPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(-1, 100), new Dimension(300, 300), null, 0, true));
        final JLabel label1 = new JLabel();
        label1.setText("Side view:");
        MainPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Front view:");
        MainPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        consoleScrollPane = new JScrollPane();
        MainPanel.add(consoleScrollPane, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        consoleScrollPane.setViewportView(console);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }
}
