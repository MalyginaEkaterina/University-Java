package ru.geekbrains;

public class JobPrint extends Job {
    private String page;
    private MFU mfu;

    public JobPrint(String page, MFU mfu) {
        this.page = page;
        this.mfu = mfu;
    }

    public void doPrint() {
        synchronized (this) {
            mfu.printPage(page);
            finished = true;
            this.notify();
        }
    }
}
