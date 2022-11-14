package com.gym;

import com.gym.controller.UserController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Application {
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection established\n" +
                        "IP:" + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                new Thread(new UserController(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
