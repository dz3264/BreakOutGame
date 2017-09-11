package Model.Bricks;

import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;

/**
 * Created by dianazhang on 2017/4/9.
 */
public class Brick {

    /**
     * Different type of bricks
     * Only have one basic type for this week
     * Normal: 1 point Normal Brick
     */
    public enum brickType{
        OneCollsion,TwoCollsion,Steel
    }

    //Brick's Type
    private brickType bType;
    //Brick's Position
    private int bX;
    private int bY;
    //Brick's break or not
    private boolean isAlive;
    //Brick's Point
    private int bPoint;
    //Drawing Bricks
    private Color mycolor;
    private String[] colors;
    public int bHeight, bWidth;
    private int strength;
    private String sound;


    public Brick(brickType bType, int bX, int bY, String[] colors){
        this.colors = colors;
        this.bType = bType;
        this.bX = bX;
        this.bY = bY;
        this.isAlive = true;
        this.bHeight = 20;
        this.bWidth = 80;

    }

    public void setPoint(int point){
        this.bPoint = point;
    }

    public void setColor(Color color){
        this.mycolor = color;
    }

    public int getStrength(){
        return this.strength;
    }

    public brickType getbType(){
        return this.bType;
    }

    public int getbX(){ return this.bX; }

    public int getbY(){ return this.bY; }

    public boolean getAlive(){
        return this.isAlive;
    }

    public int getbPoint(){
        return this.bPoint;
    }

    public void setSound(String sound){
        this.sound = sound;
    }


    public void setAlive(boolean bool){
        this.isAlive = bool;
    }

    public void setStrength(int strength){
        this.strength = strength;
    }

    public void gotCollsion(){
        playSound();
        this.strength--;
        if(strength == 0){
            this.isAlive = false;
        }
        this.changeColor();

    }

    public void drawBrick(Graphics2D g2, String[] colors){
        if(this.strength == 1){
            g2.setColor(Color.decode(colors[3]));
        }
        else if(this.strength == 2){
            g2.setColor(Color.decode(colors[4]));
        }
        else {
            g2.setColor(Color.decode(colors[5]));
        }


        g2.fillRoundRect(bX,bY,bWidth,bHeight, 5,5);
    }

    public Rectangle getRect(){
        return new Rectangle(bX,bY,bWidth,bHeight);
    }

    //change color of bricks if collsion
    public void changeColor(){

        if(this.strength == 1){
            this.mycolor = Color.decode(colors[3]);
        }

        if(this.strength == 2){
            this.mycolor = Color.decode(colors[4]);
        }
    }

    public void resetBrick(Color color, int strength){
        this.isAlive = true;
        this.mycolor = color;
        this.strength = strength;
    }


    public void playSound(){

            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.sound).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }

    }

}


//Source : http://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java