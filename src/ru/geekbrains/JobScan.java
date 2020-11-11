package ru.geekbrains;

public class JobScan extends Job {
    private MFU mfu;
    private String page;

    public JobScan(MFU mfu) {
        this.mfu = mfu;
    }

    public String getPage() {
        return page;
    }

    public void doScan() {
        synchronized (this) {
            page = mfu.scan();
            finished = true;
            this.notify();
        }
    }
}
