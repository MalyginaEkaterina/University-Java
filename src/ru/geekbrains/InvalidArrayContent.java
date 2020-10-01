package ru.geekbrains;

public class InvalidArrayContent extends RuntimeException {
    public InvalidArrayContent(int i, int j) {
        super("Некорректное содержимое ячейки (" + i + "," + j + ")");
    }
}
