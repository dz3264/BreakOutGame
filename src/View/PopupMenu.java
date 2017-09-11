package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dianazhang on 2017/4/24.
 */
public class PopupMenu{

    public JFrame popupFrame;
    private JLabel popText;

    private static final Dimension popUP_Dimension = new Dimension(300,200);

    public PopupMenu(GameMenu gMenu, GameBoard gBoard, GameFrame gFrame){
        //popup frame
        this.popupFrame = new JFrame("");
        this.popupFrame.setSize(popUP_Dimension);
        this.popupFrame.setLocationRelativeTo(null);
        this.popupFrame.setLayout(new BorderLayout());
        this.popupFrame.setResizable(false);

        //Text of Win
        this.popText = new JLabel("");
        popText.setBounds(50,30,250,100);
        popText.setFont(new Font("Cooper Black", Font.BOLD, 15));

        // restart button
        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Helvetica", Font.BOLD, 10));
        restart.setForeground(Color.decode("#27233e"));
        restart.setBounds(50,120,80,40);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupFrame.setVisible(false);
                gBoard.gameReset();
            }
        });

        //buttons
        JButton back = new JButton("Main Menu");
        back.setFont(new Font("Helvetica", Font.BOLD, 10));
        back.setForeground(Color.decode("#27233e"));
        back.setBounds(170,120,80,40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gMenu.setVisible(true);
                popupFrame.setVisible(false);
                if(gFrame.onePlayer == false){
                    gFrame.pop1.popupFrame.setVisible(false);
                    gFrame.pop2.popupFrame.setVisible(false);
                }

                gFrame.gFrame.setVisible(false);
                gFrame.clip.stop();
                gMenu.mainBGM.start();
            }
        });

        JPanel popP = new JPanel();
        popP.setLayout(null);
        popP.setBackground(Color.decode("#fbfbfd"));
        popP.add(popText);
        popP.add(back);
        popP.add(restart);

        this.popupFrame.add(popP);
        this.popupFrame.validate();
        this.popupFrame.setVisible(false);
    }

    public void winORlose(String text){
        this.popText.setText(text);
        this.popupFrame.repaint();
        this.popupFrame.validate();
        this.popupFrame.setVisible(true);
    }

}
