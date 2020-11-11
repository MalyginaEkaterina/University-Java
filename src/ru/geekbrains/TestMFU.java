package ru.geekbrains;

public class TestMFU {
    public static void main(String[] args) {
        MFU mfu = new MFU();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String s = "Hello";
                mfu.print(s);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mfu.scanToEmail();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mfu.copy();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s = "Hi";
                mfu.print(s);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String s = "How are you";
                mfu.print(s);
            }
        }).start();
    }
}
