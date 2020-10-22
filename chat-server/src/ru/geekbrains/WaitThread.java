package ru.geekbrains;

public class WaitThread extends Thread {
    private ClientThread clientThread;
    private long timeout;

    public WaitThread(ClientThread clientThread, long timeout) {
        this.clientThread = clientThread;
        this.timeout = timeout;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!clientThread.isAuthorized()) {
            clientThread.close();
        }
    }
}
