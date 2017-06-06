package com.gudigudigudi.java;

import java.io.*;
import java.net.Socket;

public class SocketHandler implements Runnable{

    final static String CRLF = "\n";

    private Socket clientSocket;

    public SocketHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            handleSocket(clientSocket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleSocket(Socket clientSocket) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream())),
                true);

        String requestHeader="";
        String s;
        while ((s=in.readLine()) !=null){
            s+=CRLF;
            requestHeader=requestHeader+s;
            if (s.equals(CRLF)){
                break;
            }
        }
        System.out.println("client request header: ");
        System.out.println(requestHeader);

        String responseBody = "client request header is: \n" + requestHeader;
        String responseHeader = "HTTP/1.0 200 OK\n" +
                "Content-Type: text/plain; charset=UTF-8\n" +
                "Content-Length: " + responseBody.getBytes().length +"\n\n";

        System.out.println("response header: ");
        System.out.println(responseHeader);

        out.write(responseHeader);
        out.write(responseBody);
        out.flush();

        out.close();
        in.close();
        clientSocket.close();
    }
}
