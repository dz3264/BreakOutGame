package Model.Bricks;

import java.awt.*;

import static Model.Bricks.Brick.brickType.*;

/**
 * Created by dianazhang on 2017/4/16.
 */
public class bTwo extends Brick {


    public bTwo(brickType bType, int bX, int bY, String[] colors) {


        super(TwoCollsion, bX, bY, colors);

        super.setSound("/Users/dianazhang/hzhan107/Final_Project_3/src/music/break.wav");
        setPoint(10);
        setColor(Color.decode(colors[4]));
        setStrength(2);

    }
    




}
