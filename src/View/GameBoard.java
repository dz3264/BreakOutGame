package View;

import Controller.BuffGenerator;
import Controller.PlayerData;
import Model.Ball;
import Model.Bar;
import Model.Buffs.*;
import Model.GameSetup;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/9.
 */
public class GameBoard extends JPanel implements ActionListener{

    //gameboard stuff
    private static Dimension gameBoard_Dimension = new Dimension(600,750);
    private Graphics2D g;
    private BufferedImage image;
    private boolean playing = false;

    private Timer timer;
    private int delay = 8;
    public int totalScore = 0;
    private int Levels;

    private int functionCount = 0;

    //models

    public BuffGenerator genBuff;
    public List<Ball> gameBall = new ArrayList<Ball>();
    private Bar gameBar;

    // if already have buffs
    public boolean capture = false;

    // setup and reset function
    private GameSetup gameSet;

    // all popup windows
    public GameFrame gFrame;

    //Color array for different theme
    //private String theme[];

    // Player Info
    public PlayerData player;

    // Check if one player or more
    // Mark different gameboard to show different popup windows
    public boolean isOneP;
    public boolean isWest;


    public GameBoard(int level,GameFrame frame, String[] colors, String playerName, boolean isOneP, boolean isWest){

        this.isWest = isWest;

        this.gFrame = frame;
        this.Levels = level;
        this.player = new PlayerData(playerName, level);

        this.setFocusable(true);
        this.requestFocus();

        this.image = new BufferedImage(600,800,BufferedImage.TYPE_INT_RGB);
        this.g = (Graphics2D) image.getGraphics();
        setBackground(Color.decode(frame.colors[0]));

        this.gameSet = new GameSetup(level,frame.colors);
        this.gameSet.setupBrick();

        this.gameBall.add(new Ball());
        this.gameBar = new Bar();
        timer = new Timer(delay,this);

        this.isOneP = isOneP;
        this.genBuff = new BuffGenerator();

        setPreferredSize(gameBoard_Dimension);
        this.validate();
        this.setVisible(true);

    }


    //for test
    public Bar getGameBar(){
        return this.gameBar;
    }

    public boolean getPlaying(){
        return this.playing;
    }

    public void gameBegin(){

        moveBar(this.gameBar);

        this.gameBar.resetBar();
        for(int i = 0; i < gameBall.size(); i++){
            gameBall.get(i).resetBall();
        }

        timer.start();
        this.playing = true;

        if(isOneP){
            this.player.setpName(this.gFrame.inputName.getPlayerName());
        }

        repaint();

    }

    public void gameReset(){

        if(isOneP){
            this.player.setPScore(this.totalScore);
            this.gFrame.playerList.add(this.player);
            this.gFrame.sHistroy.updatePlayList(this.gFrame.playerList);
        }


        if(genBuff.gameBuff!=null){
            genBuff.gameBuff.setActive(false);
        }
        genBuff.gameBuff = null;
        gameSet.totalBrick = 24;
        totalScore = 0;
        this.gameBar.resetBar();

        this.gameBall = new ArrayList<Ball>();
        this.gameBall.add(new Ball());
        this.gameBall.get(0).resetBall();
        this.gameSet.resetBrick();
        //this.player = new PlayerData("testReset", this.Levels);

        this.playing = false;

    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        // draw ball and bar
        setBackground(Color.decode(this.gFrame.colors[0]));
        for(int i = 0; i < gameBall.size(); i++){
            gameBall.get(i).drawBall(g2d, Color.decode(this.gFrame.colors[1]));
        }

        this.gameBar.drawBar(g2d, Color.decode(this.gFrame.colors[2]));
        for(int j = 0; j < gameSet.gameBricks.length; j++){
            for(int i = 0; i < gameSet.gameBricks[j].length; i++){
                if(gameSet.gameBricks[j][i].getAlive()){
                    gameSet.gameBricks[j][i].drawBrick(g2d,this.gFrame.colors);
                }
                //System.out.println(gameBricks[j][i].getRect());
            }
        }

        // Score
        g2d.setColor(Color.decode("#1a181c"));
        g2d.setFont(new Font("Helvetica", Font.PLAIN, 15));
        g2d.drawString("Score: " + totalScore, 20, 630);

        if(isOneP == false && totalScore >= 50){
            if(isWest){
                g2d.drawString(" Up Arrow Key To Give Debuff!  ", 300, 630);
            }
            else {
                g2d.drawString("Click To Give Debuff! ", 300, 630);
            }
        }


        // draw buff

        if(genBuff.gameBuff!=null && genBuff.gameBuff.getActive()){
            //gameBuff.drawBuff(g2d);
            g2d.drawImage(genBuff.gameBuff.img,genBuff.gameBuff.buX,genBuff.gameBuff.buY,60,20,this);
        }

        if(genBuff.vsBuffs.size()!=0){
            for(int i = 0; i < genBuff.vsBuffs.size(); i++){
                g2d.drawImage(genBuff.vsBuffs.get(i).img,genBuff.vsBuffs.get(i).buX,genBuff.vsBuffs.get(i).buY,60,20,this);
            }
        }


        validate();
        if(playing){

            if(isOneP && this.gFrame.popupFrame.popupFrame.isVisible() == false){
                repaint();
            }

            if(isOneP == false && this.gFrame.pop1.popupFrame.isVisible() == false){
                repaint();
            }

        }


    }

    public void moveBar(Bar gameBar){

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        // Randomly generate buff with a time gap
        if(isOneP&&genBuff.gameBuff == null){
                genBuff.generateBuff();
        }

        // check if have active buff
        if(isOneP && genBuff.gameBuff != null){

            // moving buff and check if
            genBuff.gameBuff.moveBuff();
            genBuff.gameBuff.checkCapure(gameBar);

            // count the function active time
            if(genBuff.gameBuff.getCapture()){
                //System.out.println("Get Capture");
                this.capture = true;
                // bar buff will call barFunction
                if(genBuff.gameBuff.getBuffType() == Buff.BuffType.BarBuff){
                    genBuff.gameBuff.BarFunction(gameBar);
                }
                // ball buff will call ballFunction
                else if(genBuff.gameBuff.getBuffType() == Buff.BuffType.BallBuff){
                    if(gameBall.size()<=3 && genBuff.generateBall == false){
                        genBuff.generateBall = true;
                        genBuff.gameBuff.BallFunction(gameBall);
                    }
                }
                // brick buff will call brickFunction
                else{
                    if(genBuff.strengthBrick == false){
                        genBuff.strengthBrick = true;
                        genBuff.gameBuff.BrickFunction(gameSet.gameBricks);
                    }

                }
                this.functionCount +=1;
            }

            if(capture == false && genBuff.gameBuff.buY >= 625){
                genBuff.buffGap = 0;
                genBuff.gameBuff = null;
            }


        }

        if(isOneP == false && genBuff.vsBuffs.size()!=0){

            for(int i = 0; i < genBuff.vsBuffs.size(); i++){

                genBuff.vsBuffs.get(i).moveBuff();
                genBuff.vsBuffs.get(i).checkCapure(gameBar);

                if(genBuff.vsBuffs.get(i).getCapture()) {

                    //System.out.println("Get Capture");
                    this.capture = true;
                }

            }

            // only shrink buff will appear in vs mode
            if(this.capture){
                // bar buff will call barFunction
                genBuff.vsBuffs.get(0).BarFunction(gameBar);
                this.functionCount +=1;
            }
        }


        // time count to disfunction buff
        if(this.functionCount>=1000){

            this.genBuff.buffGap = 0;
            this.capture = false;
            this.genBuff.gameBuff = null;

            this.functionCount = 0;
            //set bar to original length
            this.gameBar.setLength(100);
            //set ball to original size and speed
            this.gameBall.get(0).through=false;

        }




        // ball and brick checking during the game
        int fall = gameBall.size();
        int move = 0;
        if(playing){

            for(int i = 0; i < gameBall.size(); i++){
                // check return of moveBall, 0 for nothing, -1 fall, >0 for score
                move = gameBall.get(i).moveBall(gameBar, gameSet.gameBricks);
                //System.out.println("move: " + move);

                if(move>0){
                    totalScore += move;
                    this.gameSet.totalBrick -= 1;
                }

                // if ball fall off
                else if (move < 0){
                    fall += move;
                    gameBall.remove(i);
                }

            }

        }

        if(fall <= 0 ){

                timer.stop();
                if(gameBall.size()<=0){
                    gameBall.add(new Ball());
                }
            for(int i = 0; i < this.gameBall.size(); i++){
                this.gameBall.get(i).dx = 0;
                this.gameBall.get(i).dy = 0;
            }
                //this.gameReset();
                playing = false;
                if(this.gFrame.gFrame.isVisible()){
                    if(isOneP){
                        gFrame.popupFrame.winORlose("Sorry, You lose the game...");
                    }

                    else{
                        // west gameboard and popup windows show on west
                        if(isWest){
                            gFrame.pop1.winORlose("Sorry, You lose the game...");

                            // if one fall the ball, the other automatically win the game
                            gFrame.pop2.winORlose("Congratulation! You win!");
                        }
                        else {

                            gFrame.pop2.winORlose("Sorry, You lose the game...");
                            gFrame.pop1.winORlose("Congratulation! You win!");
                        }

                    }

                }

        }

        if(gameSet.totalBrick == 0){
            timer.stop();
            for(int i = 0; i < this.gameBall.size(); i++){
                this.gameBall.get(i).dx = 0;
                this.gameBall.get(i).dy = 0;
            }

            //this.gameReset();
            playing = false;
            if(this.gFrame.gFrame.isVisible()) {
                if(isOneP){
                    gFrame.popupFrame.winORlose("Congratulation! You win!");
                }

                else{
                    if(isWest){
                        // the player firstlly finish the ball can win the game
                        gFrame.pop1.winORlose("Congratulation! You win!");
                        gFrame.pop2.winORlose("Sorry, You lose the game...");
                    }
                    else {
                        gFrame.pop2.winORlose("Congratulation! You win!");
                        gFrame.pop1.winORlose("Sorry, You lose the game...");
                    }
                }



            }

        }


    }


}


// Source: https://www.youtube.com/watch?v=YqO6NsFapZ0
// Source: http://stackoverflow.com/questions/24220410/drawing-on-jpanel-doesnt-do-anything

//MUSIC from : Wind Fantasy