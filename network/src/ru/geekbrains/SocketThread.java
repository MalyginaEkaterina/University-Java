package ru.geekbrains;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class SocketThread extends Thread {

    private final SocketThreadListener listener;
    private final Socket socket;
    private DataOutputStream out;
    DataInputStream in;

    public SocketThread(SocketThreadListener listener, String name, Socket socket) {
        super(name);
        this.socket = socket;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            listener.onSocketStart(this, socket);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            listener.onSocketReady(this, socket);
            while (!isInterrupted()) {
//переписала так, т.к. всплывали ошибки
               String msg;
                try {
                    msg = in.readUTF();
                } catch (SocketException | EOFException e) {
                    System.out.println(e);
                    break;
                }
                listener.onReceiveString(this, socket, msg);
            }
        } catch (IOException e) {
            listener.onSocketException(this, e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                listener.onSocketException(this, e);
            }
            listener.onSocketStop(this);
        }
    }

    public synchronized boolean sendMessage(String msg) {
        try {
            out.writeUTF(msg);
//?? зачем вызывается метод flush, он вроде пустой для стрима из сокета?
            out.flush();
            return true;
        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
            return false;
        }
    }

    public synchronized void close() {
        interrupt();
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
    }
}
