package com.company.PackageExample;

import javax.swing.text.Utilities;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by gudi on 5/26/15.
 */
public class Client extends Thread {
    Socket clientSocket = null;

    public Client(Socket s) {
        clientSocket = s;
    }

    public void run() {
        if (clientSocket == null) {
            return;
        }

        PrintStream out = null;

        Utilities.printMsg("creating output stream");
    }
}
