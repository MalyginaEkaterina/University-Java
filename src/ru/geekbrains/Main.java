package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Rygik", 5);
        Cat cat2 = new Cat("Barsik", 1);
        Cat cat3 = new Cat("Pushok", 2);
        Cat cat4 = new Cat("Tisha", 1);
        Plate plate = new Plate(7);
        plate.info();

        Cat[] cats = {cat1, cat2, cat3, cat4};

        for (int i = 0; i < cats.length; i++) {
            cats[i].eat(plate);
            cats[i].printInfo();
            plate.info();
        }

        plate.addFood(10);
        plate.info();

        cat3.eat(plate);
        cat3.printInfo();
        plate.info();
    }
}
