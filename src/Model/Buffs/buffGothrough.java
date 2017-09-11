package Model.Buffs;

import Model.Ball;
import Model.Bar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by dianazhang on 2017/4/24.
 */
public class buffGothrough extends Buff {

    public buffGothrough(){
        super(BuffType.BallBuff, Color.PINK);

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/BUFFicon/throughball.png"));

        } catch (IOException e) {}
        super.setImg(image);
    }

    public void BallFunction(java.util.List<Ball> gameBall){
        if(this.getCapture()){
            gameBall.get(0).through = true;
        }

    }

}

