package Controller;

import Model.Ball;
import Model.Bar;
import Model.Bricks.Brick;
import Model.Buffs.*;
import Model.GameSetup;
import View.GameBoard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class BuffGenerator {

    //private int functionCount = 0;
    public int buffGap;
    public Buff gameBuff;
    public List<Buff> vsBuffs;

    public boolean generateBall;
    public boolean strengthBrick;

    public BuffGenerator(){
        this.gameBuff = null;
        this.vsBuffs = new ArrayList<Buff>();
        this.buffGap = 0;
        this.generateBall = false;
        this.strengthBrick = false;
    }

    public void generateBuff(){
        this.buffGap += 1;
        // in a time period, there is 50% to generate a buff
        //
        if(buffGap >= 1000 && Math.random() >= 0.5){
            double randomBuff = Math.random();


                if(randomBuff <= 0.2){
                    this.gameBuff = new buffExtend();
                }
                else if(randomBuff <= 0.4){
                    this.gameBuff = new buffShrink();
                }

                else if(randomBuff <= 0.6){
                    this.generateBall = false;
                    this.gameBuff = new buffMoreBall();
                }

                else if(randomBuff <= 0.8){
                    this.strengthBrick = false;
                    this.gameBuff = new buffAddBrick();
                }

                else{
                    this.gameBuff = new buffGothrough();
                }


            //}
        }

        else if(buffGap >= 1500){
            //System.out.println("<0.5");
            buffGap = 0;
        }


    }

    public int vsBuff(int Score, GameBoard otherPlayer){

        //otherPlayer.genBuff.vsBuffs.add(new buffShrink());

        if(Score >= 50){

            // the player with keyboard
            Score -= 50;
            otherPlayer.genBuff.vsBuffs.add(new buffShrink());

        }

        return Score;

    }


}
