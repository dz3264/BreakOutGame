package Model.Buffs;


import Model.Ball;
import Model.Bar;
import Model.Bricks.Brick;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/16.
 */
public class Buff {

    public enum BuffType{
        BarBuff,BallBuff,BrickBuff
    }

    //Brick's Type
    private BuffType buffType;
    //Brick's Position
    public int buX;
    public int buY;
    private int dy = 1;
    //appear or not
    private boolean isActive;
    //get or not
    private boolean isCapture = false;

    private Color mycolor;
    private boolean playSound;

    public Image img;

    public Buff(BuffType buffType, Color mycolor){
        this.playSound = false;
        this.buffType = buffType;
        buY = 0;
        buX = (int) (Math.random()*550);
        this.isActive = true;
        this.mycolor = mycolor;

    }

    public BuffType getBuffType(){
        return this.buffType;
    }

    public void setActive(boolean bool){
        isActive = bool;
    }

    public boolean getActive(){
        return this.isActive;
    }

    public boolean getCapture(){
        return this.isCapture;
    }

    public boolean checkCapure(Bar gameBar){

        int barX0 = (int)gameBar.getX();
        int barX1 = barX0+gameBar.getLength();
        int barY0 = gameBar.y;
        int barY1 = gameBar.y + gameBar.getHeight();

        int buffX0 = buX;
        int buffX1 = buX + 60;
        int buffY0 = buY;
        int buffY1 = buY + 20;

        // check collsion with bar
        if (buffX1 >= barX0 && buffX0 <= barX1 && buffY0 <= barY1 && buffY1 >= barY0){
            //the buff will disappear if capture

            playSound();
            this.isActive = false;
            this.isCapture = true;
            return true;

        }

        return false;
    }

    public void BarFunction(Bar gameBar){

    }

    public void BallFunction(List<Ball> gameBall){

    }

    public void BrickFunction(Brick[][] bricks){

    }

    public void moveBuff(){
        this.buY += dy;

        // if the Buff already pass
        if(this.buY > 625){
            this.isActive = false;

        }

    }

    public void setImg(BufferedImage img){

        this.img = img;

    }

    public void playSound(){

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/music/buff.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }


    }

}


// Source: https://www.youtube.com/watch?v=M-F7z1xWS6o
