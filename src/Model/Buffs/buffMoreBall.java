package Model.Buffs;

import Model.Ball;
import Model.Bar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/24.
 */
public class buffMoreBall extends Buff {


    public buffMoreBall(){
        super(BuffType.BallBuff, Color.yellow);

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/BUFFicon/multiball.png"));

        } catch (IOException e) {}
        super.setImg(image);
    }

    public void BallFunction(List<Ball> ball){
        if(this.getCapture()){
            ball.add(new Ball());
            ball.get(1).resetBall();
            ball.add(new Ball());
            ball.get(2).resetBall();
        }

    }



}
