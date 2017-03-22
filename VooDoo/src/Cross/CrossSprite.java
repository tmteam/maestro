package Cross;

import java.awt.*;

/**
 * http://docs.oracle.com/javase/tutorial/uiswing/painting/refining.html
 */

public class CrossSprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private final int crossSize = 4;
    private Color color = Color.black;

    public void SetColor(Color color){

        this.color = color;
    }

    public void setRect(int width, int height){
        this.width  = width;
        this.height = height;
    }

    public int getX(){return x;}

    public int getY(){return y;}

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g){
        g.setColor(color);

        g.drawLine(0, y,width, y);
        g.drawLine(x,0,x,height);

        g.setColor(color);
        g.fillRect(x-crossSize,y-crossSize,2*crossSize,2*crossSize);

    }
}
