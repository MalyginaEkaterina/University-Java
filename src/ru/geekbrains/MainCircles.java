package ru.geekbrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int MAX_COUNT_OF_BALLS = 30;

    Sprite[] sprites = new Sprite[MAX_COUNT_OF_BALLS];
    int countOfBalls;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        initApplication();
        add(canvas);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    addNewBall(e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    deleteBall(e.getX(), e.getY());
                }
            }
        });
        setResizable(false);
        setTitle("Circles");
        setVisible(true);
    }

    private void deleteBall(int x, int y) {
        int countOfDel = 0;
        for (int i = 0; i < countOfBalls; i++) {
            if (sprites[i].isInside(x, y)) {
                countOfDel++;
            } else {
                sprites[i - countOfDel] = sprites[i];
            }
        }
        countOfBalls -= countOfDel;
    }

    private void addNewBall(int x, int y) {
        if (countOfBalls < MAX_COUNT_OF_BALLS) {
            sprites[countOfBalls] = new Ball(x, y);
            countOfBalls++;
        }
    }

    private void initApplication() {
        countOfBalls = 10;
        for (int i = 0; i < countOfBalls; i++) {
            sprites[i] = new Ball();
        }
    }

    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime); // obnovlenie // S = v * t
        render(canvas, g);         // otrisovka
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < countOfBalls; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < countOfBalls; i++) {
            sprites[i].render(canvas, g);
        }
    }
}
