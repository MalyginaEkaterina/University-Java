package ru.geekbrains;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            socket = serverSocket.accept();
            ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Box box = (Box) inObj.readObject();
                box.printInfo();
            }
        } catch (EOFException e) {
            System.out.println("Завершение работы");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
