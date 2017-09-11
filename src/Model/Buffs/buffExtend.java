package Model.Buffs;

import Model.Bar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import static com.sun.tools.doclint.Entity.image;
import static com.sun.tools.doclint.Entity.sup;

/**
 * Created by dianazhang on 2017/4/16.
 */
public class buffExtend extends Buff {

    public buffExtend(){

        super(BuffType.BarBuff, Color.decode("#c2dac9"));

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/BUFFicon/extend.png"));

        } catch (IOException e) {}
        super.setImg(image);
    }

    public void BarFunction(Bar gameBar){
        if(this.getCapture()){
            gameBar.setLength(200);
        }

    }



}
