package brickBreaker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class Login implements ActionListener {
    JLabel label = new JLabel("or");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton signInButton = new JButton("Sign in");
    JFrame frame = new JFrame();
    JButton signUpButton = new JButton("Sign up");

    DatabaseManager db_manager = DatabaseManager.db_manager;
    Login() throws SQLException {
        Border whiteLine = BorderFactory.createLineBorder(Color.white);
        TitledBorder t1, t2;
        t1 = BorderFactory.createTitledBorder(whiteLine, "Username");
        t1.setTitleJustification(TitledBorder.LEFT);
        t1.setTitleColor(Color.white);
        usernameField.setBounds(110,100,200,40);
        usernameField.setBackground(Color.decode("#364F6B"));
        usernameField.setBorder(t1);
        usernameField.setForeground(Color.white);
        t2 = BorderFactory.createTitledBorder(whiteLine, "Password");
        t2.setTitleJustification(TitledBorder.LEFT);
        t2.setTitleColor(Color.white);
        passwordField.setBounds(110,150,200,40);
        passwordField.setBackground(Color.decode("#364F6B"));
        passwordField.setBorder(t2);
        passwordField.setForeground(Color.white);
        signInButton.setBounds(90,200,100,25);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signInButton.setBackground(Color.decode("#364F6B"));
        signInButton.setForeground(Color.decode("#6D9886"));
        signInButton.setBorder(new RoundedBorder(20));
        label.setBounds(200,200,20,25);
        label.setForeground(Color.white);
        signUpButton.setBounds(225,200,100,25);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(this);
        signUpButton.setBackground(Color.decode("#364F6B"));
        signUpButton.setForeground(Color.decode("#6D9886"));
        signUpButton.setBorder(new RoundedBorder(20));
        frame.setSize(420,420);
        frame.add(label);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.setLocationRelativeTo(null);
        frame.add(signInButton);
        frame.add(signUpButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Login");
        frame.getContentPane().setBackground(Color.decode("#364F6B"));
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signInButton){

            if (Objects.equals(usernameField.getText(), "") || Objects.equals(String.valueOf(passwordField.getPassword()), "")){
                showMessageDialog(frame, "Username and password cannot be blank! Please fill both of them!");
                }
            else{
                boolean userExists = false;
                try {
                    userExists = db_manager.userExists(usernameField.getText(), String.valueOf(passwordField.getPassword()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (userExists) {
                    frame.dispose();
                    Menu menu = new Menu(usernameField.getText());
                }
                else {
                    showMessageDialog(frame, "Your username and password did not match. Please, recheck them!");
                }

            }

        }
        else if (e.getSource() == signUpButton) {
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
