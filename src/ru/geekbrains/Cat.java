package ru.geekbrains;

public class Cat {
    private String name;
    private int appetite;
    private boolean isFull;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isFull = false;
    }

    public void eat(Plate plate) {
        if (plate.getFood() >= appetite) {
            plate.decreaseFood(appetite);
            this.isFull = true;
        }
    }

    public void printInfo() {
        System.out.println(name + " сыт: " + isFull);
    }
}
