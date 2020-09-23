package ru.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndOfGameWindow extends JFrame {
    public EndOfGameWindow(int state, MyWindow myWindow){
        setTitle("The end");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400, 400, 300, 120);
        JButton contBtn = new JButton("Начать с начала");
        JButton closeBtn = new JButton("Закрыть");
        JPanel pan = new JPanel(new GridLayout(1, 2));
        pan.add(contBtn);
        pan.add(closeBtn);
        JLabel msg = new JLabel();
        msg.setFont(new Font("Courier New", Font.BOLD, 25));
        msg.setVerticalAlignment(SwingConstants.CENTER);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        if (state == 1) {
            msg.setText("Ничья!");
        }
        if (state == 2) {
            msg.setText("Вы выиграли!");
        }
        if (state == 3) {
            msg.setText("Вы проиграли!");
        }
        add(pan, BorderLayout.SOUTH);
        add(msg, BorderLayout.CENTER);
        contBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                myWindow.setEnabled(true);
                myWindow.requestFocus();
                myWindow.setGame(myWindow.game.size);
                myWindow.field.setGame(myWindow.game);
                myWindow.field.repaint();
            }
        });
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
}
