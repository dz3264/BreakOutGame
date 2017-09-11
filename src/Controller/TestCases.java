package Controller;

import static Model.Bricks.Brick.brickType.*;
import static Model.Buffs.Buff.BuffType.BallBuff;
import static Model.Buffs.Buff.BuffType.BarBuff;
import static Model.Buffs.Buff.BuffType.BrickBuff;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.Bricks.Brick;
import Model.Bricks.bOne;
import Model.Bricks.bSteel;
import Model.Bricks.bTwo;
import Model.Buffs.*;
import View.*;
import org.junit.jupiter.api.Test;

import Model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/11.
 */
public class TestCases {

    @Test
    void myFirstTest() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void testBall(){
        Ball testBall = new Ball();
        Bar testBar = new Bar();
        Brick[][] testBricks = new Brick[1][1];
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        testBricks[0][0] = new bOne(Brick.brickType.OneCollsion,0,0, colors);

        testBar.setLength(0);
        testBricks[0][0].setAlive(false);

        int fall = 0;

        assertEquals(290, testBall.getX());
        assertEquals(585, testBall.getY());

        while(true){

            fall = testBall.moveBall(testBar,testBricks);

            if(fall == -1){
                break;
            }

        }

        assert(testBall.getY() > 575);

        testBall.resetBall();

        assertEquals(290, testBall.getX());
        assertEquals(585, testBall.getY());

        testBall.setBallSize(10);
        assertEquals(10, testBall.getBallSize());

    }

    @Test
    void testBar(){
        Bar testBar = new Bar();
        assertEquals(250, testBar.getX());

        testBar.moveBar(100);
        assertEquals(100, testBar.getX());

        testBar.moveBar(-5);
        assertEquals(0, testBar.getX());

        testBar.moveBar(700);
        assertEquals(600-testBar.getLength(), testBar.getX());

        testBar.setLength(200);
        assertEquals(200,testBar.getLength());

        testBar.resetBar();
        assertEquals(250,testBar.getX());
        assertEquals(100,testBar.getLength());

    }

    @Test
    public void testBricks(){
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        bOne testNormal = new bOne(OneCollsion,100,100,colors);

        assertEquals(OneCollsion,testNormal.getbType());
        assertEquals(true,testNormal.getAlive());
        assertEquals(100,testNormal.getbY());
        assertEquals(100,testNormal.getbX());

        testNormal.setAlive(false);
        assertEquals(false,testNormal.getAlive());


    }

    @Test
    void testGameborad(){
        GameMenu menu = new GameMenu();
        GameFrame frame = new GameFrame(1,menu,true);
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        GameBoard testBoard = new GBmouse(1,frame, colors,"Test",true);
        java.util.List<Ball> testBall = testBoard.gameBall;
        Bar testBar = testBoard.getGameBar();

        assertEquals(290, testBall.get(0).getX());
        assertEquals(585, testBall.get(0).getY());

        assertEquals(250, testBar.getX());
        assertEquals(100,testBar.getLength());

        assertEquals(false, testBoard.getPlaying());

        testBoard.gameBegin();
        assertEquals(true, testBoard.getPlaying());

        testBoard.gameReset();
        assertEquals(290, testBall.get(0).getX());
        assertEquals(585, testBall.get(0).getY());

        assertEquals(250, testBar.getX());
        assertEquals(100,testBar.getLength());
        assertEquals(false,testBoard.getPlaying());

    }

    @Test
    void testScoreHistory(){
        List<PlayerData> playerList = new ArrayList<PlayerData>();
        ScoreHistory testHistroy = new ScoreHistory(playerList);
        GameMenu menu = new GameMenu();
        GameFrame frame = new GameFrame(1,menu,true);
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        GameBoard testBoard = new GBmouse(1,frame, colors,"Testcases",true);

        assertEquals(0, testHistroy.playerList.size());

        testBoard.player.setPScore(50);
        playerList.add(testBoard.player);

        testHistroy.updatePlayList(playerList);
        assertEquals(1, testHistroy.playerList.size());
        assertEquals(50, testHistroy.playerList.get(0).getpScore());
        assertEquals("Testcases", testHistroy.playerList.get(0).getpName());

    }

    @Test
    void testExtend(){
        Buff testBuff = new buffExtend();
        Bar testBar = new Bar();

        int x = testBuff.buX;
        testBar.moveBar(x-10);

        assertEquals(true,testBuff.getActive());

        while (testBuff.buY < testBar.y){
            testBuff.moveBuff();
        }

        assertEquals(BarBuff,testBuff.getBuffType());
        assertEquals(true,testBuff.checkCapure(testBar));
        assertEquals(false,testBuff.getActive());

        testBuff.BarFunction(testBar);
        assertEquals(200,testBar.getLength());


    }

    @Test
    void testShrink(){
        Buff testBuff = new buffShrink();
        Bar testBar = new Bar();

        int x = testBuff.buX;
        testBar.moveBar(x-10);

        assertEquals(true,testBuff.getActive());

        while (testBuff.buY < testBar.y){
            testBuff.moveBuff();
        }


        assertEquals(BarBuff,testBuff.getBuffType());
        assertEquals(true,testBuff.checkCapure(testBar));
        assertEquals(false,testBuff.getActive());

        testBuff.BarFunction(testBar);
        assertEquals(50,testBar.getLength());


    }

    @Test
    void testAddBrick(){
        Buff testBuff = new buffAddBrick();
        Brick[][] testBricks = new Brick[2][6];
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        for(int j = 0; j < 1; j++){
            for(int i = 0; i < 6; i++){
                testBricks[j][i] = new bOne(Brick.brickType.OneCollsion,50+(i*85),100+(j*25),colors);

            }
        }

        for(int j = 1; j < 2; j++){
            for(int i = 0; i < 6; i++){
                testBricks[j][i] = new bTwo(Brick.brickType.TwoCollsion,50+(i*85),100+(j*25),colors);

            }
        }

        Bar testBar = new Bar();

        int x = testBuff.buX;
        testBar.moveBar(x-10);

        assertEquals(true,testBuff.getActive());
        while (testBuff.buY < testBar.y){
            testBuff.moveBuff();
        }


        assertEquals(BrickBuff,testBuff.getBuffType());
        assertEquals(true,testBuff.checkCapure(testBar));
        assertEquals(true,testBuff.getCapture());

        assertEquals(false,testBuff.getActive());

        assertEquals(1,testBricks[0][1].getStrength());
        assertEquals(2,testBricks[1][1].getStrength());

        testBuff.BrickFunction(testBricks);

        assertEquals(2,testBricks[0][1].getStrength());
        assertEquals(2,testBricks[1][1].getStrength());


    }

    @Test
    void testGoThrough(){
        Buff testBuff = new buffGothrough();
        java.util.List<Ball> testBall = new ArrayList<Ball>();
        testBall.add(new Ball());
        Bar testBar = new Bar();
        Brick[][] testBricks = new Brick[1][1];
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        testBricks[0][0] = new bTwo(Brick.brickType.TwoCollsion,0,0, colors);

        int x = testBuff.buX;
        testBar.moveBar(x-10);

        assertEquals(true,testBuff.getActive());
        while (testBuff.buY < testBar.y){
            testBuff.moveBuff();
        }


        assertEquals(BallBuff,testBuff.getBuffType());
        assertEquals(true,testBuff.checkCapure(testBar));
        assertEquals(true,testBuff.getCapture());

        assertEquals(false,testBall.get(0).through);
        testBuff.BallFunction(testBall);
        assertEquals(true,testBall.get(0).through);

    }

    @Test
    void testMoreBall(){
        Buff testBuff = new buffMoreBall();
        java.util.List<Ball> testBall = new ArrayList<Ball>();
        testBall.add(new Ball());
        Bar testBar = new Bar();

        int x = testBuff.buX;
        testBar.moveBar(x-10);

        assertEquals(true,testBuff.getActive());
        while (testBuff.buY < testBar.y){
            testBuff.moveBuff();
        }

        assertEquals(true,testBuff.checkCapure(testBar));
        assertEquals(true,testBuff.getCapture());

        assertEquals(BallBuff,testBuff.getBuffType());
        assertEquals(1,testBall.size());
        testBuff.BallFunction(testBall);
        assertEquals(3,testBall.size());

    }

    @Test
    void testBrick(){

        Brick[][] testBricks = new Brick[3][6];
        String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c"};
        for(int j = 0; j < 1; j++){
            for(int i = 0; i < 6; i++){
                testBricks[j][i] = new bOne(Brick.brickType.OneCollsion,50+(i*85),100+(j*25),colors);

            }
        }

        for(int j = 1; j < 2; j++){
            for(int i = 0; i < 6; i++){
                testBricks[j][i] = new bTwo(Brick.brickType.TwoCollsion,50+(i*85),100+(j*25),colors);

            }
        }

        for(int j = 2; j < 3; j++){
            for(int i = 0; i < 6; i++){
                testBricks[j][i] = new bSteel(Brick.brickType.Steel,50+(i*85),100+(j*25),colors);

            }
        }

        assertEquals(50, testBricks[0][0].getbX());
        assertEquals(100, testBricks[0][0].getbY());
        assertEquals(50, testBricks[1][0].getbX());
        assertEquals(125, testBricks[1][0].getbY());
        assertEquals(50, testBricks[2][0].getbX());
        assertEquals(150, testBricks[2][0].getbY());

        assertEquals(5, testBricks[0][1].getbPoint());
        assertEquals(10, testBricks[1][1].getbPoint());
        assertEquals(0, testBricks[2][1].getbPoint());

        assertEquals(1,testBricks[0][1].getStrength());
        assertEquals(2,testBricks[1][1].getStrength());
        assertEquals(100000,testBricks[2][1].getStrength());

        assertEquals(Brick.brickType.OneCollsion,testBricks[0][1].getbType());
        assertEquals(Brick.brickType.TwoCollsion,testBricks[1][1].getbType());
        assertEquals(Brick.brickType.Steel,testBricks[2][1].getbType());


        testBricks[0][1].gotCollsion();
        testBricks[1][1].gotCollsion();
        testBricks[2][1].gotCollsion();

        assertEquals(false, testBricks[0][1].getAlive());
        assertEquals(true, testBricks[1][1].getAlive());
        assertEquals(true, testBricks[2][1].getAlive());
        assertEquals(1,testBricks[1][1].getStrength());

        testBricks[1][1].gotCollsion();
        assertEquals(false, testBricks[0][1].getAlive());

        testBricks[0][1].resetBrick(Color.decode(colors[1]),1);
        testBricks[1][1].resetBrick(Color.decode(colors[2]),2);

        assertEquals(1,testBricks[0][1].getStrength());
        assertEquals(2,testBricks[1][1].getStrength());
        assertEquals(true, testBricks[0][1].getAlive());
        assertEquals(true, testBricks[1][1].getAlive());

    }



}
