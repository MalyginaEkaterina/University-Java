package ru.geekbrains;

import java.awt.*;

public class Background {
    long lastChangeTime;
    GameCanvas canvas;

    Background(GameCanvas canvas) {
        lastChangeTime = System.nanoTime();
        this.canvas = canvas;
        changeBackground();
    }

    void changeBackground() {
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastChangeTime) * 0.000000001f;
        if (deltaTime > 5) {
            canvas.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            lastChangeTime = currentTime;
        }
    }
}
