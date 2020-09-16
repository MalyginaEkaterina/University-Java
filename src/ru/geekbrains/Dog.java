package ru.geekbrains;

public class Dog extends Animal{

    public Dog(String name){
        super(name, 500, 10, 0.5f);
    }

    public Dog(String name, float limRun, float limSwim, float limJump) {
        super(name, limRun, limSwim, limJump);
    }
}
