package ru.geekbrains;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel {
    private Game game;

    public Field(Game game) {
        this.game = game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        int Width = getWidth();
        int Height = getHeight();
        int WidthOfCell = Width / game.size;
        int HeightOfCell = Height / game.size;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        for (int i = 1; i < game.size; i++) {
            g2.drawLine((WidthOfCell) * i, 0, (WidthOfCell) * i, Height);
        }
        for (int i = 1; i < game.size; i++) {
            g2.drawLine(0, (HeightOfCell) * i, Width, (HeightOfCell) * i);
        }
        g2.setStroke(new BasicStroke(10));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 0; i < game.size; i++) {
            for (int j = 0; j < game.size; j++) {
                if (i == game.lastMove[0] && j == game.lastMove[1]) {
                    g2.setColor(Color.RED);
                } else {
                    g2.setColor(Color.BLACK);
                }
                if (game.field[i][j] == game.USER_CHAR) {
                    g2.drawLine(WidthOfCell * j + WidthOfCell / 10, HeightOfCell * i + HeightOfCell / 10, WidthOfCell * (j + 1) - WidthOfCell / 10, HeightOfCell * (i + 1) - HeightOfCell / 10);
                    g2.drawLine(WidthOfCell * j + WidthOfCell / 10, HeightOfCell * (i + 1) - HeightOfCell / 10, WidthOfCell * (j + 1) - WidthOfCell / 10, HeightOfCell * i + HeightOfCell / 10);
                }
                if (game.field[i][j] == game.COMP_CHAR) {
                    g2.drawOval(WidthOfCell * j + WidthOfCell / 10, HeightOfCell * i + HeightOfCell / 10, WidthOfCell - WidthOfCell / 5, HeightOfCell - HeightOfCell / 5);
                }
            }
        }

    }
}
