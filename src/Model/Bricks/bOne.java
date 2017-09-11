/**
 * Created by dianazhang on 2017/4/9.
 */
package Model.Bricks;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.spi.AudioFileReader;
import java.applet.AudioClip;
import java.awt.*;

public class bOne extends Brick{


    public bOne(brickType bType, int bX, int bY, String[] colors) {


        super(brickType.OneCollsion, bX, bY, colors);


        super.setSound("/Users/dianazhang/hzhan107/Final_Project_3/src/music/break.wav");
        super.setPoint(5);
        super.setColor(Color.decode(colors[3]));
        super.setStrength(1);

    }


}
