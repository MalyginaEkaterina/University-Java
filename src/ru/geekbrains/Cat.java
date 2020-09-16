package ru.geekbrains;

public class Cat extends Animal{

    public Cat(String name){
        super(name, 200, 0, 2);
    }

    public Cat(String name, float limRun, float limJump) {
        super(name, limRun, 0, limJump);
    }

    @Override
    public void swim(float dist){
        System.out.println(name + " не умеет плавать");
    }
}
