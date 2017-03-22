package Cross;

import javax.swing.*;
import java.awt.*;

public class CrossPanelView extends JPanel {

    private ICrossModel model;

    private final CrossSprite cross = new CrossSprite();
    private final CrossSprite crossFeedback = new CrossSprite();


    public CrossPanelView() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void SetModel(ICrossModel model){

        this.model = model;
        this.model.addXYListener(new XYListener() {
            @Override
            public void XYUpdated(ICrossModel sender, double x, double y) {
                paint(crossFeedback,(int)convertX(x), (int)convertY(y));
            }
        });
        paint(crossFeedback,(int)model.getCurrentX(),(int) model.getCurrentY());
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
        // Draw Text
        //g.drawString("This is my custom Panel!",10,20);
        cross.setRect(this.getWidth(), this.getHeight());
        crossFeedback.setRect(this.getWidth(), this.getHeight());

        cross.paint(g);
        crossFeedback.paint(g);
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
    private double convertX(double x){
        double minX = model.getMinX();
        double maxX = model.getMaxX();

        if(x<=minX)
            return 0;

        int width = getWidth();

        if(x>=maxX)
            return width;

        return width * ((x-minX)/(maxX-minX));
    }

    private double convertY(double y){
        double minY = model.getMinY();
        double maxY = model.getMaxY();

        if(y<=minY)
            return 0;

        int height = getHeight();

        if(y>=maxY)
            return height;

        return height* ((y-minY)/(maxY-minY));
    }
    public CrossSprite getCrossFeedback(){
        return  crossFeedback;
    }
    public CrossSprite getCross() {
        return cross;
    }
}
