package ru.geekbrains;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood(){
        return this.food;
    }

    public void info() {
        System.out.println("food: " + this.food);
    }

    public void decreaseFood(int n){
        food -= n;
    }

    public void addFood(int n){
        food += n;
    }
}
