package Model;

import java.awt.*;

/**
 * Created by dianazhang on 2017/4/10.
 */
public class Bar {

    //size of bar
    private int width = 100;
    private int height = 15;

    //position of bar
    private double x = 250;
    public final int y = 600;

    public Bar(){

    }

    public int getLength() {

        return this.width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * For functional use
     * @param length: may enlarge or shrink by functional bricks
     */
    public void setLength(int length){

        this.width = length;
    }

    public double getX(){
        return this.x;
    }

    // draw the move bar
    public void moveBar(int mouseX){

        this.x = mouseX ;

        if (this.x < 0){
            this.x = 0;
        }
        if (this.x >= (600-width)){
            this.x = (600-width);
        }

    }

    public void resetBar(){

        this.x = 250;
        this.width = 100;
    }

    public void drawBar(Graphics2D g, Color color){
        g.setColor(color);
        g.fillRoundRect((int)x,y,width,height,1,1);

    }


}

// Source: https://www.youtube.com/watch?v=CZ6i8RM6Uxg