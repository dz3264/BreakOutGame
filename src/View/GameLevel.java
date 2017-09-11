package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class GameLevel extends JPanel {


    private static Dimension gameFrame_Dimension = new Dimension(600,400);

    public GameLevel(GameMenu menu, boolean isOne){


        this.setLayout(null);
        this.setBackground(Color.decode("#dbebf6"));

        //Text on jFrame -> jLabel
        JLabel gLabel = new JLabel("Breakout Game");
        gLabel.setBounds(180,30,400,100);
        gLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
        gLabel.setForeground(Color.decode("#416c85"));

        //Level one Button

        JButton bLV1 = new JButton("Level 1");
        bLV1.setFont(new Font("Helvetica", Font.BOLD, 15));
        bLV1.setForeground(Color.decode("#27233e"));
        bLV1.setBounds(100,150,100,40);
        bLV1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.mainBGM.stop();

                if(isOne){
                    menu.gameBegin(1,true);
                }
                else {
                    menu.gameBegin(1,false);
                }

            }
        });

        //Level two Button

        JButton bLV2 = new JButton("Level 2");
        bLV2.setFont(new Font("Helvetica", Font.BOLD, 15));
        bLV2.setForeground(Color.decode("#27233e"));
        bLV2.setBounds(250,150,100,40);
        bLV2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.mainBGM.stop();
                if(isOne){
                    menu.gameBegin(2,true);
                }
                else {
                    menu.gameBegin(2,false);
                }
            }
        });

        //Level three Button

        JButton bLV3 = new JButton("Level 3");
        bLV3.setFont(new Font("Helvetica", Font.BOLD, 15));
        bLV3.setForeground(Color.decode("#27233e"));
        bLV3.setBounds(400,150,100,40);
        bLV3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.mainBGM.stop();
                if(isOne){
                    menu.gameBegin(3,true);
                }
                else {

                    menu.gameBegin(3,false);
                }
            }
        });


        //Exit Button
        JButton bExit = new JButton("Back");
        bExit.setFont(new Font("Helvetica", Font.BOLD, 15));
        bExit.setForeground(Color.decode("#27233e"));
        bExit.setBounds(250,300,100,40);
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                menu.backToPlayer();
            }
        });


        //jPanel
        this.add(gLabel);
        this.add(bLV1);
        this.add(bLV2);
        this.add(bLV3);
        this.add(bExit);

    }


}

