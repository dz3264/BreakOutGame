package Model.Bricks;

import java.awt.*;

/**
 * Created by dianazhang on 2017/4/23.
 */
public class bSteel extends Brick{



    public bSteel(brickType bType, int bX, int bY, String[] colors) {


        super(brickType.Steel, bX, bY, colors);

        super.setSound("/Users/dianazhang/hzhan107/Final_Project_3/src/music/block.wav");
        super.setPoint(0);
        super.setColor(Color.decode(colors[5]));
        super.setStrength(100000);

    }
}
