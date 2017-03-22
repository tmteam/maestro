package Cross;

import javax.swing.*;
import java.awt.*;

public class CrossPanelView extends JPanel {

    private final CrossSprite cross = new CrossSprite();
    private final CrossSprite crossFeedback = new CrossSprite();
    private String description;

    public CrossPanelView() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(300,300);
    }
    public Dimension getMinimumSize()   {
        return new Dimension(300,300);
    }
    public Dimension getMaximumSize(){
        return new Dimension(300,300);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width =this.getWidth();
        int height = this.getHeight();
        g.setColor(Color.darkGray);
        g.fillRect(0,0, width,height);

        if(description!=null) {
            g.setColor(Color.lightGray);
            g.setFont(Font.getFont(Font.MONOSPACED+" 8"));
            int lineNum = 1;
            for (String line: description.split("\r\n")) {
                g.drawString(line, 5, lineNum*14);
                lineNum++;
            }
        }
        cross.setRect(width,height);
        crossFeedback.setRect(width, height);
        cross.paint(g);
        crossFeedback.paint(g);
    }

    public void SetDescription(String description){
        this.description = description;
        repaint(0,0,this.getWidth(), this.getHeight());
    }


    public void paint(CrossSprite sprite, int x, int y) {
        final int CURR_X = sprite.getX();
        final int CURR_Y = sprite.getY();

        if ((CURR_X!=x) || (CURR_Y!=y)) {
            // The square is moving, repaint background
            // over the old square location.
            repaint(0,0,this.getWidth(), this.getHeight());
            sprite.setXY(x,y);
            repaint(0,0,this.getWidth(), this.getHeight());
        }
    }

    public CrossSprite getCrossFeedback(){
        return  crossFeedback;
    }
    public CrossSprite getCross() {
        return cross;
    }
}
