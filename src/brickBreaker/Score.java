package brickBreaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Score implements ActionListener {
    JFrame frame = new JFrame();
    JLabel label1 = new JLabel("");
    JLabel label2 = new JLabel("");
    JLabel label3 = new JLabel("");
    JLabel label4 = new JLabel("");
    JLabel label5 = new JLabel("");
    JLabel label6 = new JLabel("");
    JLabel label7 = new JLabel("");
    JLabel label8 = new JLabel("");
    JLabel label9 = new JLabel("");
    JLabel label10 = new JLabel("");
    JButton b1 = new JButton("Exit");
    Score(String[][] ls){
//        label1.setText(Arrays.toString(ls[0]));
//        if (ls[0][0] != null){
//            label1.setBounds(100,100,500,25);
//        }
//        else {
//            label1.setText("--------------------------------------------------");
//            label1.setBounds(100,100,500,25);
//        }
//        label2.setText(Arrays.toString(ls[1]));
//        if (ls[1][0] != null){
//            label2.setBounds(100,150,500,25);
//        }
//        else {
//            label2.setText("--------------------------------------------------");
//            label2.setBounds(100,150,500,25);
//        }
//        label3.setText(Arrays.toString(ls[2]));
//        if (ls[2][0] != null){
//            label3.setBounds(100,200,500,25);
//        }
//        else {
//            label3.setText("--------------------------------------------------");
//            label3.setBounds(100,200,500,25);
//        }
//        label4.setText(Arrays.toString(ls[3]));
//        if (ls[3][0] != null){
//            label4.setBounds(100,250,500,25);
//        }
//        else {
//            label4.setText("--------------------------------------------------");
//            label4.setBounds(100,250,500,25);
//        }
//        label5.setText(Arrays.toString(ls[4]));
//        if (ls[4][0] != null){
//            label5.setBounds(100,300,500,25);
//        }
//        else {
//            label5.setText("--------------------------------------------------");
//            label5.setBounds(100,300,500,25);
//        }
//        label6.setText(Arrays.toString(ls[5]));
//        if (ls[5][0] != null){
//            label6.setBounds(100,350,500,25);
//        }
//        else {
//            label6.setText("--------------------------------------------------");
//            label6.setBounds(100,350,500,25);
//        }
//        label7.setText(Arrays.toString(ls[6]));
//        if (ls[6][0] != null){
//            label7.setBounds(100,400,500,25);
//        }
//        else {
//            label7.setText("--------------------------------------------------");
//            label7.setBounds(100,400,500,25);
//        }
//        label8.setText(Arrays.toString(ls[7]));
//        if (ls[7][0] != null){
//            label8.setBounds(100,450,500,25);
//        }
//        else {
//            label8.setText("--------------------------------------------------");
//            label8.setBounds(100,450,500,25);
//        }
//        label9.setText(Arrays.toString(ls[8]));
//        if (ls[8][0] != null){
//            label9.setBounds(100,500,500,25);
//        }
//        else {
//            label9.setText("--------------------------------------------------");
//            label9.setBounds(100,500,500,25);
//        }
//        label10.setText(Arrays.toString(ls[9]));
//        if (ls[9][0] != null){
//            label10.setBounds(100,550,500,25);
//        }
//        else {
//            label10.setText("--------------------------------------------------");
//            label10.setBounds(100,550,500,25);
//        }
//        b1.setBounds(500,600,550,25);
//        frame.add(label1);
//        frame.add(label2);
//        frame.add(label3);
//        frame.add(label4);
//        frame.add(label5);
//        frame.add(label6);
//        frame.add(label7);
//        frame.add(label8);
//        frame.add(label9);
//        frame.add(label10);
        String[] columnName = {"Rank","Username", "Score"};
        JTable table = new JTable(ls,columnName);
        table.setBounds(0,0,500,700);
        frame.add(table);
        frame.setSize(500,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Lieder Board");
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            frame.dispose();
        }
    }
}