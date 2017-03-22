package Cross;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Su on 22/03/17.
 */
public class CrossPanelPresenter {

    private CrossPanelView view;
    private ICrossModel model;

    public CrossPanelPresenter(ICrossModel model,CrossPanelView view){
        SetView(view);
        SetModel(model);
    }

    private void SetModel(ICrossModel model){

        this.model = model;
        this.model.addXYListener(new XYListener() {
            @Override
            public void currentXYUpdated(ICrossModel sender, double x, double y) {
                view.paint(view.getCrossFeedback(),(int) convertToGx(x), (int) convertToGy(y));
                UpdateDescription();
            }

            @Override
            public void targetXYUpdated(ICrossModel sender, double x, double y) {
                view.paint(view.getCross(),(int) convertToGx(x), (int) convertToGy(y));
                UpdateDescription();

            }
        });
        view.paint(view.getCrossFeedback(),(int) convertToGx(model.getCurrentX()), (int) convertToGy(model.getCurrentY()));
        view.paint(view.getCross(),(int) convertToGx(model.getTargetX()), (int) convertToGy(model.getTargetY()));
        UpdateDescription();
    }

    private int cursorX;
    private int cursorY;

    private void  UpdateDescription(){
        String description = "current: "+ (int)model.getTargetX()+", "+ (int)model.getTargetY()+"\r\n"+
                "target : "+ (int)model.getCurrentX()+", "+ (int)model.getCurrentY()+"\r\n"+
                "cursor : "+ cursorX+", "+ cursorY;
        view.SetDescription(description);

    }
    private void  SetView(CrossPanelView view){
        this.view = view;
        view.getCrossFeedback().SetColor(Color.lightGray);
        view.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { mouseDrag(e);}
        });

        view.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) { mouseDrag(e);}
            public void mouseMoved(MouseEvent e){ mouseMove(e); }
        });

    }
    void  mouseMove(MouseEvent e){
        cursorX = (int)toModelX(e.getX());
        cursorY = (int)toModelY(e.getY());
        UpdateDescription();
    }
    void mouseDrag(MouseEvent e){
        model.setTarget(toModelX(e.getX()),toModelY(e.getY()));
        mouseMove(e);
    }

    private double toModelY(int gy) {
        return model.getMinY()   + (gy/(double)view.getHeight())*(model.getMaxY()- model.getMinY());
    }

    private double toModelX(int gx) {
        return model.getMinX()   + (gx/(double)view.getWidth())*(model.getMaxX()- model.getMinX());
    }

    private double convertToGx(double x){
        double minX = model.getMinX();
        double maxX = model.getMaxX();

        if(x<=minX)
            return 0;

        int width = view.getWidth();

        if(x>=maxX)
            return width;

        return width * ((x-minX)/(maxX-minX));
    }

    private double convertToGy(double y){
        double minY = model.getMinY();
        double maxY = model.getMaxY();

        if(y<=minY)
            return 0;

        int height = view.getHeight();

        if(y>=maxY)
            return height;

        return height* ((y-minY)/(maxY-minY));
    }
}


