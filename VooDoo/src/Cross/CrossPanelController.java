package Cross;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Su on 22/03/17.
 */
public class CrossPanelController {

    private CrossPanelView view;
    private ICrossModel model;

    public CrossPanelController(ICrossModel model){
        this.model = model;

    }
    
    public void  SetView(CrossPanelView view){
        this.view = view;
        view.getCrossFeedback().SetColor(Color.lightGray);
        view.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { mouseDrag(e.getX(),e.getY());}
        });

        view.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) { mouseDrag(e.getX(),e.getY());}
        });

    }
    void mouseDrag(int gx, int gy){
        view.paint(view.getCross(), gx,gy);
        double x =  model.getMinX()   + (gx/(double)view.getWidth())*(model.getMaxX()- model.getMinX());
        double y =  model.getMinY()   + (gy/(double)view.getHeight())*(model.getMaxY()- model.getMinY());
        model.SetCurrent(x,y);
    }
}


