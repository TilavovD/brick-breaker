package brickBreaker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login implements ActionListener {
    JLabel label = new JLabel("or");
    JTextField tf = new JTextField();
    JPasswordField pwd = new JPasswordField();
    JButton b1 = new JButton("Sign in");
    JFrame frame = new JFrame();
    JButton b2 = new JButton("Sign up");
    Login(){
        Border whiteLine = BorderFactory.createLineBorder(Color.white);
        TitledBorder t1, t2;
        t1 = BorderFactory.createTitledBorder(whiteLine, "Username");
        t1.setTitleJustification(TitledBorder.LEFT);
        t1.setTitleColor(Color.white);
        tf.setBounds(110,100,200,40);
        tf.setBackground(Color.decode("#364F6B"));
        tf.setBorder(t1);
        tf.setForeground(Color.white);
        t2 = BorderFactory.createTitledBorder(whiteLine, "Password");
        t2.setTitleJustification(TitledBorder.LEFT);
        t2.setTitleColor(Color.white);
        pwd.setBounds(110,150,200,40);
        pwd.setBackground(Color.decode("#364F6B"));
        pwd.setBorder(t2);
        pwd.setForeground(Color.white);
        b1.setBounds(90,200,100,25);
        b1.setFocusable(false);
        b1.addActionListener(this);
        b1.setBackground(Color.decode("#364F6B"));
        b1.setForeground(Color.decode("#6D9886"));
        b1.setBorder(new RoundedBorder(20));
        label.setBounds(200,200,20,25);
        label.setForeground(Color.white);
        b2.setBounds(225,200,100,25);
        b2.setFocusable(false);
        b2.addActionListener(this);
        b2.setBackground(Color.decode("#364F6B"));
        b2.setForeground(Color.decode("#6D9886"));
        b2.setBorder(new RoundedBorder(20));
        frame.setSize(420,420);
        frame.add(label);
        frame.add(tf);
        frame.add(pwd);
        frame.add(b1);
        frame.add(b2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.getContentPane().setBackground(Color.decode("#364F6B"));
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            frame.dispose();
            Menu menu = new Menu();
        }
        else if (e.getSource() == b2) {
            frame.dispose();
            try {
                SignUp signUp = new SignUp();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}
