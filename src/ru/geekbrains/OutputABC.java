package ru.geekbrains;

//1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
// Используйте wait/notify/notifyAll.
public class OutputABC {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        OutputABC outputABC = new OutputABC();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                outputABC.printA();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                outputABC.printB();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                outputABC.printC();
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.println("A");
                    currentLetter = 'B';
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

        public void printB () {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'B') {
                            monitor.wait();
                        }
                        System.out.println("B");
                        currentLetter = 'C';
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void printC () {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'C') {
                            monitor.wait();
                        }
                        System.out.println("C");
                        currentLetter = 'A';
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
