package ru.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] strArr = {"a", "b", "c", "d", "e"};
        System.out.println(Arrays.toString(strArr));
        changeArrElements(strArr, 2, 4);
        System.out.println(Arrays.toString(strArr));
        Integer[] intArr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(intArr));
        changeArrElements(intArr, 0, 3);
        System.out.println(Arrays.toString(intArr));
        ArrayList<String> strArrList = toArrList(strArr);
        System.out.println("strArrList: " + strArrList);
        ArrayList<Integer> intArrList = toArrList(intArr);
        System.out.println("intArrList: " + intArrList);

        //3. Большая задача:
        Box<Apple> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();
        Apple apple4 = new Apple();
        Orange orange1 = new Orange();
        box1.addFruit(apple1);
        box1.addFruit(apple2);
        box2.addFruit(apple3);
        box2.addFruit(apple4);
        box3.addFruit(orange1);
        System.out.println("box1 " + box1 + " weight " + box1.getWeight());
        System.out.println("box2 " + box2 + " weight " + box2.getWeight());
        System.out.println("box3 " + box3 + " weight " + box3.getWeight());
        System.out.println(box1.compare(box2));
        System.out.println(box1.compare(box3));
        box1.pourOver(box2);
        System.out.println("box1 " + box1 + " weight " + box1.getWeight());
        System.out.println("box2 " + box2 + " weight " + box2.getWeight());

    }

    //1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);
    public static <T> void changeArrElements(T[] arr, int i, int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    //2. Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> toArrList(T[] arr) {
        ArrayList<T> arrList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(arr[i]);
        }
        return arrList;
    }
}
