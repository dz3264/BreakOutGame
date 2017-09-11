package View;

import Controller.PlayerData;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;


/**
 * Created by dianazhang on 2017/4/9.
 */
public class GameFrame extends JFrame{

    public GameMenu gMenu;
    public JFrame gFrame;

    public GameBoard gBoard;
    public GameBoard westBoard;
    public GameBoard eastBoard;
    public PopupMenu popupFrame;
    public PopupMenu pop1;
    public PopupMenu pop2;

    public InputName inputName;
    public ScoreHistory sHistroy;
    public List<PlayerData> playerList = new ArrayList<PlayerData>();

    private String player = "player";
    private String player1 = "player1";
    private String player2 = "player2";

    public Clip clip;

    public boolean onePlayer;

    private static final Dimension onePlayer_Dimension = new Dimension(600,750);
    private static final Dimension twoPlayer_Dimension = new Dimension(1350,750);

    public String[] colors = {"#fcfcfe","#6ba1b9","#01688c","#7dadd3","#3d4652","#1a181c","/Users/dianazhang/hzhan107/Final_Project_3/src/music/bgm.wav"};

    public GameFrame(int level, GameMenu gMenu, boolean onePlayer) {
        this.gMenu = gMenu;
        this.onePlayer = onePlayer;


        this.gFrame = new JFrame("Brick Break Game");
        if(onePlayer){
            this.gFrame.setSize(onePlayer_Dimension);
            this.gBoard = new GBmouse(level,this, colors,player, true);
            this.inputName = new InputName();

        }
        else {
            this.gFrame.setSize(twoPlayer_Dimension);
            this.westBoard = new GBkey(level,this, colors,player1);
            this.eastBoard = new GBmouse(level,this, colors,player2, false);
        }

        this.gFrame.setLocationRelativeTo(null);
        this.gFrame.setLayout(new BorderLayout());
        this.gFrame.setResizable(false);

        if(onePlayer){
            this.gFrame.add(gBoard);
            this.popupFrame = new PopupMenu(gMenu, gBoard, this);
        }
        else {
            this.gFrame.add(westBoard, BorderLayout.WEST);
            this.gFrame.add(eastBoard, BorderLayout.EAST);
            this.pop1 = new PopupMenu(gMenu, westBoard, this);
            this.pop1.popupFrame.setLocation(200, 350);
            this.pop2 = new PopupMenu(gMenu, eastBoard, this);
            this.pop2.popupFrame.setLocation(950, 350);
        }

        final JMenuBar menuBar = createMenu();
        this.gFrame.setJMenuBar(menuBar);


        this.sHistroy = new ScoreHistory(this.playerList);

        this.gFrame.validate();
        this.gFrame.setVisible(true);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(colors[6]).getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);

        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private JMenuBar createMenu(){
        final JMenuBar menuBar = new JMenuBar();
        menuBar.add(GameMenu());
        return menuBar;
    }

    private JMenu GameMenu(){
        final JMenu gameMenu = new JMenu("Game");

        final JMenuItem startGame = new JMenuItem("Start Game");
        startGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //clip.start();
                if(onePlayer){
                    gBoard.gameBegin();

                }
                else {
                    westBoard.gameBegin();
                    eastBoard.gameBegin();
                }
            }
        });
        gameMenu.add(startGame);

        final JMenu theme = new JMenu("Change Theme");
        theme.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        gameMenu.add(theme);

        final JMenuItem T0 = new JMenuItem("Default Blue");
        T0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                colors[0] ="#fcfcfe";
                colors[1] ="#6ba1b9";
                colors[2] ="#01688c";
                colors[3] ="#7dadd3";
                colors[4] ="#3d4652";
                colors[5] ="#1a181c";
                colors[6] = "/Users/dianazhang/hzhan107/Final_Project_3/src/music/bgm.wav";
                clip.stop();
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(colors[6]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                if(onePlayer){
                    gBoard.repaint();

                }
                else {
                    westBoard.repaint();
                    eastBoard.repaint();
                }
            }
        });
        theme.add(T0);

        final JMenuItem T1 = new JMenuItem("Lavender");
        T1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                colors[0] ="#fbfbfd";
                colors[1] ="#bdccff";
                colors[2] ="#8c9de2";
                colors[3] ="#f0e2f8";
                colors[4] ="#bea4d2";
                colors[5] ="#674483";
                colors[6] ="/Users/dianazhang/hzhan107/Final_Project_3/src/music/pretty.wav";
                clip.stop();
                try {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(colors[6]).getAbsoluteFile());
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch(Exception ex) {
                    System.out.println("Error with playing sound.");
                    ex.printStackTrace();
                }
                if(onePlayer){
                    gBoard.repaint();

                }
                else {
                    westBoard.repaint();
                    eastBoard.repaint();
                }

            }
        });
        theme.add(T1);

        final JMenuItem scoreHistory = new JMenuItem("Score History");
        scoreHistory.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sHistroy.scoreFrame.setVisible(true);

            }
        });
        gameMenu.add(scoreHistory);

        final JMenuItem exitGame = new JMenuItem("Main Menu");
        exitGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                gMenu.setVisible(true);
                gFrame.setVisible(false);
                clip.stop();
                gMenu.mainBGM.start();
            }
        });
        gameMenu.add(exitGame);



        return gameMenu;
    }





}


//Source : Java GUI tutorial  https://www.youtube.com/watch?v=mjOicuXEvwg&list=PLA11B442106673455