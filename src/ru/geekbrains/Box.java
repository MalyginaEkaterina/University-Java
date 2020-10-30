package ru.geekbrains;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        if (!fruits.isEmpty()) {
            return fruits.size() * fruits.get(0).getWeight();
        } else {
            return 0.0f;
        }
    }

    public boolean compare(Box<?> otherBox) {
        return Math.abs(this.getWeight() - otherBox.getWeight()) < 0.00001;
    }

    public void pourOver(Box<T> otherBox) {
        for (T fruit : fruits) {
            otherBox.addFruit(fruit);
        }
        this.fruits.clear();
    }

    @Override
    public String toString() {
        return "Box{" +
                "fruits=" + fruits +
                '}';
    }
}
