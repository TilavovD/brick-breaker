package brickBreaker;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Score implements ActionListener {
    JFrame frame;
    JButton b1 = new JButton("Exit");
    Score(String[][] data){
        frame = new JFrame();

        String[] columnNames = {"Rank", "Username", "Score"};

        JTable table = new JTable(data, columnNames);
//        table.setBounds(0,0,480,800);

        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(10, 10, 480, 800);

        table.setFillsViewportHeight(true);


        table.setRowHeight(40);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 20));
        table.setFont(new Font("San Serif", Font.PLAIN,20));

        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );

        b1.setBounds(400,400,100,25);
        b1.setFocusable(false);
        b1.addActionListener(this);

        JPanel container = new JPanel();
        container.add(scrollPane);
        container.add(b1);
        frame.add(container);
        frame.getContentPane().setBackground(Color.decode("#364F6B"));
        frame.setSize(500,520);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Leaderboard");

        frame.setLocationRelativeTo(null);
        frame.setFocusable(false);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){
            frame.dispose();

        }
    }
}