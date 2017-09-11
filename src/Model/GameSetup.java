package Model;

import Model.Bricks.Brick;
import Model.Bricks.bOne;
import Model.Bricks.bSteel;
import Model.Bricks.bTwo;

import java.awt.*;

/**
 * Created by dianazhang on 2017/4/23.
 */
public class GameSetup {

    private int level = 0;
    public Brick[][] gameBricks;
    public int totalBrick = 0;
    private String theme[];
    // all set up functions

    public GameSetup(int lv, String[] colors){
        this.theme = colors;
        this.level = lv;
        if(lv == 1 || lv == 2){
            this.totalBrick = 24;
        }

    }

    //set up bricks
    public void setupBrick(){

        if(level == 1){

            this.gameBricks = new Brick[4][6];
            for(int j = 2; j < 4; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i] = new bOne(Brick.brickType.OneCollsion,50+(i*85),50+(j*25),theme);

                }
            }

            for(int j = 0; j < 2; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i] = new bTwo(Brick.brickType.TwoCollsion,50+(i*85),50+(j*25),theme);

                }
            }
        }

        if(level == 2){

            this.gameBricks = new Brick[4][7];

            for(int j = 0; j < 2; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i] = new bTwo(Brick.brickType.TwoCollsion,50+(i*85),50+(j*25),theme);

                }
            }

            for(int j = 2; j < 4; j++){
                for(int i = 0; i < 3; i++){
                    gameBricks[j][i] = new bOne(Brick.brickType.OneCollsion,30+(i*85),175+((j-2)*25),theme);

                }
            }

            for(int j = 2; j < 4; j++){
                for(int i = 3; i < 6; i++){
                    gameBricks[j][i] = new bOne(Brick.brickType.OneCollsion,70+(i*85),175+((j-2)*25),theme);

                }
            }


            for(int j = 0; j < 4; j++){
                gameBricks[j][6] = new bSteel(Brick.brickType.Steel, 50+(j*141),125,theme);
            }



        }

    }
    public void resetBrick(){

        if(level == 1){
            for(int j = 2; j < 4; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i].resetBrick(Color.decode(theme[3]),1);
                }
            }
            for(int j = 0; j < 2; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i].resetBrick(Color.decode(theme[4]),2);
                }
            }
        }

        if(level == 2){
            for(int j = 0; j < 2; j++){
                for(int i = 0; i < 6; i++){
                    gameBricks[j][i].resetBrick(Color.decode(theme[4]),2);

                }
            }

            for(int j = 2; j < 4; j++){
                for(int i = 0; i < 3; i++){
                    gameBricks[j][i].resetBrick(Color.decode(theme[3]),1);

                }
            }

            for(int j = 2; j < 4; j++){
                for(int i = 3; i < 6; i++){
                    gameBricks[j][i].resetBrick(Color.decode(theme[3]),1);

                }
            }


            for(int j = 0; j < 4; j++){
                gameBricks[j][6].resetBrick(Color.decode(theme[5]),100000);
            }
        }


    }

    //reset bricks

    //set up colors
    public void setupColor(int theme, Color[] colors){

    }

}
