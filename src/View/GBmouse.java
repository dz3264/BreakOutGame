package View;

import Model.Bar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class GBmouse extends GameBoard {



    public GBmouse(int level,GameFrame frame, String[] colors, String playerName, boolean isOneP){
        super(level, frame, colors, playerName, isOneP, false);
    }

    public void moveBar(Bar gameBar){
        this.addMouseMotionListener(new MouseMotionListener(){

            public void mouseDragged(MouseEvent e) {}

            public void mouseMoved(MouseEvent e) {

                gameBar.moveBar(e.getX());

            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click");
                GBmouse.super.totalScore = GBmouse.super.genBuff.vsBuff(GBmouse.super.totalScore, GBmouse.super.gFrame.westBoard);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }



}
