package brickBreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help  implements ActionListener {
    JFrame frame = new JFrame();
    JButton button = new JButton("Exit");
    JLabel label = new JLabel("introduction to game");
    Help(){
        label.setBounds(150,100,140,140);
        button.setBounds(300,350,100,25);
        button.setFocusable(false);
        button.addActionListener(this);
        frame.setSize(420,420);
        frame.add(label);
        frame.add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Help");
        frame.getContentPane().setBackground(Color.BLUE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            frame.dispose();
            Menu menu = new Menu();
        }
    }
}
