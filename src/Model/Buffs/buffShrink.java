package Model.Buffs;

import Model.Bar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dianazhang on 2017/4/16.
 */
public class buffShrink extends Buff {

    public buffShrink(){
        super(BuffType.BarBuff, Color.decode("#eb514c"));

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/BUFFicon/shrink.png"));

        } catch (IOException e) {}
        super.setImg(image);
    }

    public void BarFunction(Bar gameBar){
        if(this.getCapture()){
            gameBar.setLength(50);
        }

    }

}
