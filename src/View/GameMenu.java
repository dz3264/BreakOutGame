package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by dianazhang on 2017/4/23.
 */
public class GameMenu extends JFrame {

    private JPanel gPlayerOne;
    private JPanel gPlayerTwo;
    private JPanel gPlayers;

    public Clip mainBGM;

    private static Dimension gameFrame_Dimension = new Dimension(600,400);

    public GameMenu() {
        //Basic setting of jFrame
        setTitle("Brick Break Game");
        this.setSize(gameFrame_Dimension);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        this.gPlayers = new GamePlayers(this);
        this.gPlayerOne = new GameLevel(this, true);
        this.gPlayerTwo = new GameLevel(this, false);

        this.getContentPane().add(gPlayers);

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/dianazhang/hzhan107/Final_Project_3/src/music/mainBgm.wav").getAbsoluteFile());
            this.mainBGM = AudioSystem.getClip();
            this.mainBGM.open(audioInputStream);
            this.mainBGM.start();
            this.mainBGM.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

        validate();
        setVisible(true);
    }

    public void gameBegin(int lvs, boolean onePlayer){
        this.setVisible(false);
        GameFrame game = new GameFrame(lvs,this, onePlayer);
        game.clip.start();

    }

    public void levelSelection(boolean isOne){
        this.gPlayers.setVisible(false);

        if(isOne){
            this.gPlayerOne.setVisible(true);
            this.getContentPane().add(gPlayerOne);
        }
        else {
            this.gPlayerTwo.setVisible(true);
            this.getContentPane().add(gPlayerTwo);
        }


        repaint();
        validate();
        setVisible(true);
    }



    public void backToPlayer(){
        this.gPlayers.setVisible(true);
        repaint();
        validate();
        setVisible(true);

    }


}
