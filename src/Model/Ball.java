package Model;

import Model.Bricks.Brick;
import Model.Bricks.bTwo;

import java.awt.*;
import java.util.Random;



/**
 * Created by dianazhang on 2017/4/9.
 */
public class Ball {

    private int x, y;
    public double dx, dy;
    // functional variables
    private double speed;
    private int ballSize = 15;
    public boolean through = false;

    public Ball(){
        this.x = 290;
        this.y = 585;

        Random gen = new Random();
        //Random pn = new Random()
        int i = gen.nextInt(2);

        if(i==0){
            this.dx = -(gen.nextInt(2)+1);
        }
        else {
            this.dx = gen.nextInt(2)+1;
        }
        this.dy = -(gen.nextInt(2)+1);

        this.speed = 1;
    }

    public int getX(){
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getBallSize(){
        return this.ballSize;
    }

    /**
     * For later functional use
     * @param newSize: functional grid will help to enlarge or shrink the size of ball
     */
    public void setBallSize(int newSize){
        this.ballSize = newSize;
    }


    public void resetBall(){
        this.x = 290;
        this.y = 585;

        Random gen = new Random();
        int i = gen.nextInt(2);

        if(i==0){
            this.dx = -(gen.nextInt(2)+1)*speed;
        }
        else {
            this.dx = (gen.nextInt(2)+1)*speed;
        }
        this.dy = -3*speed;
    }

    public void drawBall(Graphics2D g, Color color){
        g.setColor(color);
        g.fillOval(x,y,ballSize,ballSize);
    }

    public Rectangle getRect(){
        return new Rectangle(x,y,ballSize,ballSize);
    }

    public int moveBall(Bar gameBar, Brick[][] gameBricks){
        this.x += dx;
        this.y += dy;

        //System.out.println("Ball x: " + this.x );
        //System.out.println("Ball y: " + this.y );

        // left
        if(x <= 1){
            this.dx = (-this.dx)*speed;
        }

        // right
        if(x >= (600-ballSize)){
            this.dx = (-this.dx)*speed;
        }

        // up
        if(y <= 1){
            this.dy = (-this.dy)*speed;
        }

        //game bar
        if(y >= 585 && x >= (gameBar.getX()-5) && x <= (gameBar.getX()+ gameBar.getLength() +5)){
            // ball's direction change by position of collsion

            // middle of Bar & ball
            double midBar = gameBar.getX()+(gameBar.getLength()/2);
            double midBall = x + (ballSize/2);

            // larger will lead to more direction deviate
            // negative if on the right side of bar
            //System.out.println("Diverge: " + (midBall-midBar));
            double diverge = midBall - midBar;

            this.dx = (int)diverge/10 * speed;
            //System.out.println("dx: " + dx);


            this.dy = -3*speed;
        }

        // down
        if(y > 588 && (x < (gameBar.getX()-5) || x > (gameBar.getX()+ gameBar.getLength()+5))) {
            // end of game
            return -1;
        }

        return checkCollsion(gameBricks);

    }

    public int checkCollsion(Brick[][] gameBricks){
        int top = 0;
        int buttom = 0;
        int right = 0;
        int left = 0;
        Brick temp = null;
        Rectangle rec = null;
        //if ball in the bricks area
            A: for (int a = 0; a < gameBricks.length; a++){
                for (int b = 0; b < gameBricks[a].length; b++){

                    //check all alive bricks
                    if(gameBricks[a][b].getAlive()){

                            temp = gameBricks[a][b];
                            top = temp.getbY();
                            buttom = temp.getbY()+20;
                            left = temp.getbX();
                            right = temp.getbX()+80;

                            rec = gameBricks[a][b].getRect();

                            // may fix the bug that ball go through between two bricks
                            if(rec.intersects(this.getRect())){

                                if(this.through==false){

                                    if((x+14) <= left || (x+1) >= right){
                                        this.dx = -dx;
                                    }
                                    else {
                                        this.dy = -dy;
                                    }
                                }

                                gameBricks[a][b].gotCollsion();
                                if(gameBricks[a][b].getAlive()==false){
                                    return gameBricks[a][b].getbPoint();
                                }


                            break A;

                            }



                        }
                }
            }



        return 0;
    }




}


// Source https://www.youtube.com/watch?v=oynZhQjMv0c