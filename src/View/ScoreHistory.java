package View;

import Controller.PlayerData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by dianazhang on 2017/4/24.
 */
public class ScoreHistory {

    public JFrame scoreFrame;
    public List<PlayerData> playerList;
    public JPanel scoreP;
    private static final Dimension popUP_Dimension = new Dimension(250, 500);

    public ScoreHistory(List<PlayerData> playerList) {
        //popup frame
        this.scoreFrame = new JFrame("");
        this.scoreFrame.setSize(popUP_Dimension);
        this.scoreFrame.setLocationRelativeTo(null);
        this.scoreFrame.setLayout(new BorderLayout());
        this.scoreFrame.setResizable(false);

        this.playerList = playerList;

        // score text
        JLabel scoreText = new JLabel("Score History");
        scoreText.setBounds(50,20,250,40);
        scoreText.setFont(new Font("Cooper Black", Font.BOLD, 20));


        JLabel scorePlayer = new JLabel("Player");
        scorePlayer.setBounds(20,60,50,40);
        scorePlayer.setFont(new Font("Cooper Black", Font.PLAIN, 15));

        JLabel scoreScore = new JLabel("Score");
        scoreScore.setBounds(170,60,50,40);
        scoreScore.setFont(new Font("Cooper Black", Font.PLAIN, 15));

        // restart button
        JButton ok = new JButton("OK");
        ok.setFont(new Font("Helvetica", Font.BOLD, 10));
        ok.setForeground(Color.decode("#27233e"));
        ok.setBounds(85,400,80,40);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreFrame.setVisible(false);

            }
        });

        this.scoreP = new JPanel();
        scoreP.setLayout(null);
        scoreP.setBackground(Color.decode("#fbfbfd"));
        scoreP.add(scoreText);
        scoreP.add(scorePlayer);
        scoreP.add(scoreScore);
        scoreP.add(ok);

        //showScores();

        this.scoreFrame.add(scoreP);
        this.scoreFrame.validate();
        this.scoreFrame.setVisible(false);

    }

    public void updatePlayList(List<PlayerData> playerList){

        this.playerList = playerList;
        showScores();
        this.scoreFrame.repaint();

    }

    // show all input score on Score History
    public void showScores(){

        List<JLabel> pNames = new ArrayList<JLabel>();
        List<JLabel> pScores = new ArrayList<JLabel>();

        if(this.playerList.size() != 0){
            //System.out.println("PlayList: "+ this.playerList.size());
            for (int i = 0; i < this.playerList.size(); i++){
                //System.out.println("i: "+i);
                //System.out.println("pName Size: "+pNames.size());
                pNames.add(new JLabel(this.playerList.get(i).getpName()));
                pScores.add(new JLabel(Integer.toString(this.playerList.get(i).getpScore())));

                pNames.get(i).setBounds(20,80+(i*20),100,40);
                pNames.get(i).setFont(new Font("Helvetica", Font.BOLD, 10));

                pScores.get(i).setBounds(170,80+(i*20),50,40);
                pScores.get(i).setFont(new Font("Helvetica", Font.BOLD, 10));

                scoreP.add(pNames.get(i));
                scoreP.add(pScores.get(i));

            }

        }

    }

}
