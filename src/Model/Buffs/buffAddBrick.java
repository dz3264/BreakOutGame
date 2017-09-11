package Model.Buffs;

import Model.Ball;
import Model.Bricks.Brick;

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
public class buffAddBrick extends Buff {

    public buffAddBrick(){
        super(BuffType.BrickBuff, Color.blue);

        BufferedImage image = null;

        try{
            image = ImageIO.read(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/BUFFicon/strength.png"));

        } catch (IOException e) {}
        super.setImg(image);
    }

    public void BrickFunction(Brick[][] bricks){
        if(this.getCapture()){
            for(int j = 0; j < bricks.length; j++){
                for(int i = 0; i < bricks[j].length; i++){
                    if(bricks[j][i].getAlive()){
                        if(bricks[j][i].getStrength() == 1){
                            bricks[j][i].setStrength(2);
                        }
                    }

                }
            }
        }

    }

}
