package ru.geekbrains;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class MFU {
    private ArrayDeque<JobPrint> printQueue = new ArrayDeque<>();
    private ArrayDeque<JobScan> scanQueue = new ArrayDeque<>();

    public MFU() {
        Thread threadPrint = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    JobPrint j;
                    synchronized (printQueue) {
                        while (printQueue.isEmpty()) {
                            try {
                                printQueue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        j = printQueue.pollFirst();
                    }
                    j.doPrint();
                }
            }
        });
        Thread threadScan = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    JobScan j;
                    synchronized (scanQueue) {
                        while (scanQueue.isEmpty()) {
                            try {
                                scanQueue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        j = scanQueue.pollFirst();
                    }
                    j.doScan();
                }
            }
        });
        threadPrint.start();
        threadScan.start();
    }

    public void printPage(String page) {
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("printed.txt", true))) {
            out.write(page + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanToEmail() {
        JobScan jobScan = new JobScan(this);
        synchronized (scanQueue) {
            scanQueue.addLast(jobScan);
            scanQueue.notify();
        }
        jobScan.waitUntilFinish();
        sendOnEMail(jobScan.getPage());
    }

    public void sendOnEMail(String page) {
        System.out.println("Отправлено на почту: " + page);
    }

    public String scan() {
        System.out.println("Введите документ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void copy() {
        JobScan jobScan = new JobScan(this);
        synchronized (scanQueue) {
            scanQueue.addLast(jobScan);
            scanQueue.notify();
        }
        jobScan.waitUntilFinish();
        print(jobScan.getPage());
    }

    public void print(String page) {
        JobPrint jobPrint = new JobPrint(page, this);
        synchronized (printQueue) {
            printQueue.addLast(jobPrint);
            printQueue.notify();
        }
        jobPrint.waitUntilFinish();
    }
}
