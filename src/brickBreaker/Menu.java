package brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Menu implements ActionListener {
    JButton b1 = new JButton("Play");
    JButton b2 = new JButton("Score");
    JButton b4 = new JButton("Quit");
    JFrame frame = new JFrame();
    String username;
    Image img = Toolkit.getDefaultToolkit().getImage("brickBreaker\\menu.jpg");
    Menu(String username){
        this.username = username;
        b1.setBounds(160,100,100,25);
        b1.setFocusable(false);
        b1.addActionListener(this);
        b2.setBounds(160,150,100,25);
        b2.setFocusable(false);
        b2.addActionListener(this);
        b4.setBounds(160,200,100,25);
        b4.setFocusable(false);
        b4.addActionListener(this);
        frame.setSize(420,420);
        frame.add(b1);
        frame.add(b2);
        frame.setLocationRelativeTo(null);
        frame.add(b4);

        frame.getContentPane().setBackground(Color.decode("#364F6B"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu");
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            frame.dispose();
            JFrame frame = new JFrame();

            //Create gameplay object where all commands processed
            GameSettings gamePlay = null;
            try {
                gamePlay = new GameSettings(username);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            //Set frame size fullscreen
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

            //Set title of frame
            frame.setTitle("Brick Breaker");

            //Disable resizing of window
            frame.setResizable(false);
//

            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gamePlay);
            frame.setVisible(true);
        }
        else if (e.getSource() == b2) {
            try {
                Score leaderboard = new Score(DatabaseManager.db_manager.getLeaderboard());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource() == b4) {
            frame.dispose();
        }
    }
}
