package ru.geekbrains;

public class Job {
    protected boolean finished;

    public void waitUntilFinish() {
        synchronized (this) {
            while (!finished) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
