package Cross;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CrossPanelPresenter {

    private CrossPanelView view;
    private ICrossModel model;
    private int cursorX;
    private int cursorY;
    private String title;

    public CrossPanelPresenter(ICrossModel model,CrossPanelView view, String title){
        this.title = title;
        setView(view);
        setModel(model);
    }

    private void setModel(ICrossModel model){

        this.model = model;
        this.model.addXYListener(new XYListener() {
            @Override
            public void currentXYUpdated(ICrossModel sender, double x, double y) {
                view.paint(view.getCrossFeedback(),(int) toViewGx(x), (int) toViewGy(y));
                updateDescription();
            }

            @Override
            public void targetXYUpdated(ICrossModel sender, double x, double y) {
                if(Math.abs(sender.getTargetX()-sender.getCurrentX())>10){

                }
                view.paint(view.getCross(),(int) toViewGx(x), (int) toViewGy(y));
                updateDescription();

            }
        });
        view.paint(view.getCrossFeedback(),(int) toViewGx(model.getCurrentX()), (int) toViewGy(model.getCurrentY()));
        view.paint(view.getCross(),(int) toViewGx(model.getTargetX()), (int) toViewGy(model.getTargetY()));
        updateDescription();
    }

    private void setView(CrossPanelView view){
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

    private void  mouseMove(MouseEvent e){
        cursorX = (int)toModelX(e.getX());
        cursorY = (int)toModelY(e.getY());
        updateDescription();
    }

    private void  mouseDrag(MouseEvent e){
        model.setTarget(toModelX(e.getX()),toModelY(e.getY()));
        mouseMove(e);
    }

    private void updateDescription(){
        String description = title+"\r\n"+
                "current: "+ (int)model.getTargetX()+", "+ (int)model.getTargetY()+"\r\n"+
                "target  : "+ (int)model.getCurrentX()+", "+ (int)model.getCurrentY()+"\r\n"+
                "cursor : "+ cursorX+", "+ cursorY;
        view.SetDescription(description);

    }

    private double toModelY(int gy) {
        return model.getMinY()   + (gy/(double)view.getHeight())*(model.getMaxY()- model.getMinY());
    }

    private double toModelX(int gx) {
        return model.getMinX()   + (gx/(double)view.getWidth())*(model.getMaxX()- model.getMinX());
    }

    private double toViewGx(double x){
        double minX = model.getMinX();
        double maxX = model.getMaxX();

        if(x<=minX)
            return 0;

        int width = view.getWidth();

        if(x>=maxX)
            return width;

        return width * ((x-minX)/(maxX-minX));
    }

    private double toViewGy(double y){
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


