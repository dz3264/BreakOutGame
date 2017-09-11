package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class GamePlayers extends JPanel {
    private static Dimension gameFrame_Dimension = new Dimension(600,400);


    public GamePlayers(GameMenu menu){


        this.setLayout(null);
        this.setBackground(Color.decode("#dbebf6"));

        //Text on jFrame -> jLabel
        JLabel gLabel = new JLabel("Breakout Game");
        gLabel.setBounds(180,30,400,100);
        gLabel.setFont(new Font("Cooper Black", Font.BOLD, 30));
        gLabel.setForeground(Color.decode("#416c85"));

        // one Player Button

        JButton bP1 = new JButton("One Player");
        bP1.setFont(new Font("Helvetica", Font.BOLD, 20));
        bP1.setForeground(Color.decode("#27233e"));
        bP1.setBounds(150,150,150,80);
        bP1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.levelSelection(true);
            }
        });

        // two Player Button

        JButton bP2 = new JButton("Two Players");
        bP2.setFont(new Font("Helvetica", Font.BOLD, 20));
        bP2.setForeground(Color.decode("#27233e"));
        bP2.setBounds(300,150,150,80);
        bP2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.levelSelection(false);
            }
        });

        //Level three Button


        //Exit Button
        JButton bExit = new JButton("Exit");
        bExit.setFont(new Font("Helvetica", Font.BOLD, 15));
        bExit.setForeground(Color.decode("#27233e"));
        bExit.setBounds(250,300,100,40);
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //jPanel
        this.add(gLabel);
        this.add(bP1);
        this.add(bP2);
        this.add(bExit);

    }
}
