package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dianazhang on 2017/4/29.
 */
public class InputName {

    public JFrame InFrame = new JFrame("Input Player Name");
    private String playerName = "Player";

    private static final Dimension popUP_Dimension = new Dimension(300,200);

    public InputName(){

        this.InFrame.setSize(popUP_Dimension);
        this.InFrame.setLocationRelativeTo(null);
        this.InFrame.setLayout(new BorderLayout());
        this.InFrame.setResizable(false);
        this.InFrame.setAlwaysOnTop(true);

        // input area
        JTextField inputName = new JTextField();
        inputName.setBounds(100,50,100,40);


        // restart button
        JButton ok = new JButton("OK");
        ok.setFont(new Font("Helvetica", Font.BOLD, 10));
        ok.setForeground(Color.decode("#27233e"));
        ok.setBounds(110,120,80,40);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = inputName.getText();
                if(playerName.length() < 1){
                    playerName = "Player";
                }
                InFrame.setVisible(false);
            }
        });


        JPanel InPanel = new JPanel();
        InPanel.setLayout(null);
        InPanel.setBackground(Color.decode("#fbfbfd"));
        InPanel.add(inputName);
        InPanel.add(ok);

        this.InFrame.add(InPanel);
        this.InFrame.validate();
        this.InFrame.setVisible(true);

    }

    public String getPlayerName(){
        return this.playerName;
    }

}
