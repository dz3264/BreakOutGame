package View;

import Model.Bar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class GBkey extends GameBoard {

    public GBkey(int level,GameFrame frame, String[] colors, String playerName){
        super(level, frame, colors, playerName, false, true);
    }

    public void moveBar(Bar gameBar){
        this.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent e) {

                int key = e.getKeyCode();

                double xPosition = gameBar.getX();

                if (key == KeyEvent.VK_LEFT) {

                    gameBar.moveBar((int)(xPosition-20));

                }

                if (key == KeyEvent.VK_RIGHT) {

                    gameBar.moveBar((int)(xPosition+20));

                }

                if (key == KeyEvent.VK_UP) {

                    GBkey.super.totalScore = GBkey.super.genBuff.vsBuff(GBkey.super.totalScore, GBkey.super.gFrame.eastBoard);

                }

            }

            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
