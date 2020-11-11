package ru.geekbrains;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        final String SERVER_ADDR = "localhost";
        final int SERVER_PORT = 8189;

        try (Socket socket = new Socket(SERVER_ADDR, SERVER_PORT)) {
            ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
            Box box1 = new Box("box 1", 25, 45);
            Box box2 = new Box("box 2", 90, 60);
            outObj.writeObject(box1);
            outObj.writeObject(box2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
