package ru.geekbrains;

import java.io.Serializable;

public class Box implements Serializable {
    private String name;
    private int width;
    private int height;

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public void printInfo() {
        System.out.println("name: " + name + " width: " + width + " height: " + height);
    }
}
