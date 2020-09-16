package ru.geekbrains;

public class Main {

    public static void main(String[] args) {
        Cat cat1 = new Cat("Barsik");
        Dog dog1 = new Dog("Sharik");

        cat1.run(150);
        cat1.run(250);
        cat1.swim(100);
        cat1.jump(2);
        cat1.jump(3);

        dog1.run(450);
        dog1.run(550);
        dog1.swim(5);
        dog1.swim(15);
        dog1.jump(0.4f);
        dog1.jump(0.9f);

        Cat cat2 = new Cat("Pushok", 140, 1);
        cat2.run(150);
        cat2.swim(100);
        cat2.jump(2);

        Dog dog2 = new Dog("Bobik", 300, 4, 0.3f);
        dog2.run(450);
        dog2.swim(5);
        dog2.jump(0.4f);

    }
}
