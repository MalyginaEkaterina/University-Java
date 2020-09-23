package ru.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyWindow extends JFrame {

    public final int WINDOW_WIDTH = 500;
    public final int WINDOW_HEIGHT = 500;
    protected Game game;
    protected Field field;

    public MyWindow(int size) {
        setGame(size);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, WINDOW_WIDTH, WINDOW_HEIGHT);
        JButton button3 = new JButton("3x3");
        JButton button5 = new JButton("5x5");
        field = new Field(game);
        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        panel2.add(button3);
        panel2.add(button5);
        add(field, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGame(3);
                field.setGame(game);
                field.repaint();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGame(5);
                field.setGame(game);
                field.repaint();
            }
        });
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int i = e.getY() / (field.getHeight() / game.size);
                int j = e.getX() / (field.getWidth() / game.size);
                game.gameIteration(i, j);
                field.repaint();
                checkEnd();
            }
        });

        setVisible(true);
    }

    public void checkEnd(){
        if (game.state != 0) {
            this.setEnabled(false);
            EndOfGameWindow endOfGameWindow = new EndOfGameWindow(game.state, this);
        }
    }

    public void setGame(int s) {
        this.game = new Game(s);
    }
}
