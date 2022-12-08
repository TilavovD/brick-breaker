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
    JLabel label1 = new JLabel("Do you prefer conform by E-mail or phone number");
    JRadioButton radioButton1 = new JRadioButton("E-mail");
    JRadioButton radioButton2 = new JRadioButton("Phone number");
    JTextField usernameField = new JTextField();
    JTextField emailField = new JTextField();
    JTextField passwordField = new JPasswordField();
    JTextField phoneNumberField = new JTextField();
    JLabel label = new JLabel("or");
    JButton submitButton = new JButton("Submit");
    JFrame frame = new JFrame();
    JButton signInButton = new JButton("Sign in");
    Border whiteLine = BorderFactory.createLineBorder(Color.white);

    DatabaseManager db_manager;
    SignUp() throws SQLException {
        this.db_manager = new DatabaseManager();
        TitledBorder t1, t2, t3, t4;
        t1 = BorderFactory.createTitledBorder(whiteLine, "Username ");
        t1.setTitleJustification(TitledBorder.LEFT);
        t1.setTitleColor(Color.white);
        usernameField.setBounds(110,100,200,40);
        usernameField.setBackground(Color.decode("#364F6B"));
        usernameField.setBorder(t1);
        usernameField.setForeground(Color.white);
        usernameField.setCaretColor(Color.white);
        t2 = BorderFactory.createTitledBorder(whiteLine, "E-mail ");
        t2.setTitleJustification(TitledBorder.LEFT);
        t2.setTitleColor(Color.white);
        emailField.setBounds(110,150,200,40);
        emailField.setBackground(Color.decode("#364F6B"));
        emailField.setBorder(t2);
        emailField.setForeground(Color.white);
        t3 = BorderFactory.createTitledBorder(whiteLine, "Password");
        t3.setTitleJustification(TitledBorder.LEFT);
        t3.setTitleColor(Color.white);
        passwordField.setBounds(110,200,200,40);
        passwordField.setBackground(Color.decode("#364F6B"));
        passwordField.setBorder(t3);
        passwordField.setForeground(Color.white);
        t4 = BorderFactory.createTitledBorder(whiteLine, "Phone number ");
        t4.setTitleJustification(TitledBorder.LEFT);
        t4.setTitleColor(Color.white);
        phoneNumberField.setBounds(110,250,200,40);
        phoneNumberField.setBackground(Color.decode("#364F6B"));
        phoneNumberField.setBorder(t4);
        phoneNumberField.setForeground(Color.white);
        submitButton.setBounds(90,350,100,25);
        submitButton.setFocusable(false);
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.decode("#364F6B"));
        submitButton.setForeground(Color.decode("#6D9886"));
        submitButton.setBorder(new Login.RoundedBorder(20));
        label.setBounds(200,350,20,25);
        label.setForeground(Color.white);
        signInButton.setBounds(225,350,100,25);
        signInButton.setFocusable(false);
        signInButton.addActionListener(this);
        signInButton.setBackground(Color.decode("#364F6B"));
        signInButton.setForeground(Color.decode("#6D9886"));
        signInButton.setBorder(new Login.RoundedBorder(20));
        label1.setBounds(60,300,400,25);
        label1.setForeground(Color.white);
        radioButton1.setBounds(100,320,100,25);
        radioButton1.setBackground(Color.decode("#364F6B"));
        radioButton1.setForeground(Color.white);
        radioButton2.setBounds(225,320,150,25);
        radioButton2.setBackground(Color.decode("#364F6B"));
        radioButton2.setForeground(Color.white);
        ButtonGroup bg=new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        frame.setSize(420,500);
        frame.add(label);
        frame.add(usernameField);
        frame.add(emailField);
        frame.add(passwordField);
        frame.add(phoneNumberField);
        frame.add(submitButton);
        frame.add(signInButton);
        frame.add(label1);
        frame.add(radioButton1);
        frame.add(radioButton2);

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
            if (Objects.equals(usernameField.getText(), "") || Objects.equals(passwordField.getText(), "")){
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
