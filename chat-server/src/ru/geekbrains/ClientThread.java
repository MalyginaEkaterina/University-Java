package ru.geekbrains;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorized;
    private boolean isReconnecting;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
        new WaitThread(this, 120000l);
    }

    public String getNickname() {
        return nickname;
    }

    public synchronized boolean isAuthorized() {
        return isAuthorized;
    }

    public boolean isReconnecting() {
        return isReconnecting;
    }

    void reconnect() {
        isReconnecting = true;
        close();
    }

   synchronized void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Library.getAuthAccept(nickname));
    }

    void authFail() {
        sendMessage(Library.getAuthDenied());
        close();
    }

    void msgFormatError(String msg) {
        sendMessage(Library.getMsgFormatError(msg));
        close();
    }
}
