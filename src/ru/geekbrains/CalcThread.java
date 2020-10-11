package ru.geekbrains;

public class CalcThread extends Thread {
    private float[] arr;
    private int shift;

    public CalcThread(int shift, float[] arr) {
        this.shift = shift;
        this.arr = arr;
    }

    @Override
    public void run() {
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + shift * arrLength) / 5) * Math.cos(0.2f + (i + shift * arrLength) / 5) * Math.cos(0.4f + (i + shift * arrLength) / 2));
        }
    }
}
