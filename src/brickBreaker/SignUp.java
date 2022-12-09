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

public class SignUp implements ActionListener {
    JTextField usernameField = new JTextField();
    JTextField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    JLabel label = new JLabel("or");
    JButton submitButton = new JButton("Submit");
    JFrame frame = new JFrame();
    JButton signInButton = new JButton("Sign in");
    Border whiteLine = BorderFactory.createLineBorder(Color.white);

    DatabaseManager db_manager = DatabaseManager.db_manager;
    SignUp() throws SQLException {
        TitledBorder t1, t2, t3;
        t1 = BorderFactory.createTitledBorder(whiteLine, "Username ");
        t1.setTitleJustification(TitledBorder.LEFT);
        t1.setTitleColor(Color.white);
        usernameField.setBounds(110,100,200,40);
        usernameField.setBackground(Color.decode("#364F6B"));
        usernameField.setBorder(t1);
        usernameField.setForeground(Color.white);
        usernameField.setCaretColor(Color.white);
        t3 = BorderFactory.createTitledBorder(whiteLine, "Password");
        t3.setTitleJustification(TitledBorder.LEFT);
        t3.setTitleColor(Color.white);
        passwordField.setBounds(110,150,200,40);
        passwordField.setBackground(Color.decode("#364F6B"));
        passwordField.setBorder(t3);
        passwordField.setForeground(Color.white);
        passwordField.setCaretColor(Color.white);
        t2 = BorderFactory.createTitledBorder(whiteLine,"Confirm password");
        t2.setTitleJustification(TitledBorder.LEFT);
        t2.setTitleColor(Color.white);
        confirmPasswordField.setBounds(110,200,200,40);
        confirmPasswordField.setBackground(Color.decode("#364F6B"));
        confirmPasswordField.setBorder(t2);
        confirmPasswordField.setForeground(Color.white);
        confirmPasswordField.setCaretColor(Color.white);
        submitButton.setBounds(90,250,100,25);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.decode("#364F6B"));
        submitButton.setForeground(Color.decode("#6D9886"));
        submitButton.setBorder(new Login.RoundedBorder(20));
        label.setBounds(200,250,20,25);
        label.setForeground(Color.white);
        signInButton.setBounds(225,250,100,25);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signInButton.setBackground(Color.decode("#364F6B"));
        signInButton.setForeground(Color.decode("#6D9886"));
        signInButton.setBorder(new Login.RoundedBorder(20));
        frame.setSize(420,420);
        frame.add(label);
        frame.add(usernameField);
        frame.add(confirmPasswordField);
        frame.setLocationRelativeTo(null);
        frame.add(passwordField);
        frame.add(submitButton);
        frame.add(signInButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sign Up");
        frame.getContentPane().setBackground(Color.decode("#364F6B"));
        frame.setLayout(null);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean usernameExists;
        if(e.getSource() == submitButton){
            if (Objects.equals(usernameField.getText(), "") || Objects.equals(passwordField.getText(), "") ||  Objects.equals(String.valueOf(confirmPasswordField.getPassword()), "")){
                showMessageDialog(frame, "Username and password cannot be blank! Please fill both of them!");
               }
            else{
                try {
                usernameExists = this.db_manager.usernameExists(usernameField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (usernameExists){
                    showMessageDialog(null, "User with this username already exists. Please, choose another one!");

                }
                else{
                    if(passwordField.getText().length() < 4 || passwordField.getText().length() >8) {
                        showMessageDialog(null, "Length of password must be more than 4 and less than 8");
                    }
                    else{
                        if(!Objects.equals(passwordField.getText(), String.valueOf(confirmPasswordField.getPassword()))) {
                            showMessageDialog(null, "Your password did not match! Please, recheck and reenter!");
                        }
                        else {

                            try {
                                db_manager.registerUser(usernameField.getText(), passwordField.getText());
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }


                            frame.dispose();
                            try {
                                Login login = new Login();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }

                    }
                }
            }

        }
        else if (e.getSource() == signInButton) {
            frame.dispose();
            try {
                Login login = new Login();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    private static class RoundedBorder implements Border {
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
