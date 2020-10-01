package ru.geekbrains;

public class InvalidArrayContentException extends RuntimeException {
    public InvalidArrayContentException(int i, int j) {
        super("Некорректное содержимое ячейки (" + i + "," + j + ")");
    }
}
