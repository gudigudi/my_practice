package com.gudigudigudi.java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyHTTPServer {

    public static void main(String[] args) throws IOException {

        int port = 8000;

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("start service: binding port: " + port);

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(30);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("new link" + clientSocket.getInetAddress() + ":" +
                    clientSocket.getPort());

            try {
                fixedThreadPool.execute(new SocketHandler(clientSocket));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
