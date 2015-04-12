package com.company;

import java.io.*;

/**
 * Created by gudi on 4/12/15.
 */
public class DataInputTest {
    public static void main(String[] args) throws IOException {
        DataInputStream d = new DataInputStream(new
                FileInputStream("test.txt"));

        DataOutputStream out = new DataOutputStream(new
                FileOutputStream("test1.txt"));

        String count;
        while ((count = d.readLine()) != null) {
            String u = count.toUpperCase();
            System.out.println(u);
            out.writeBytes(u + "  ,");
        }
        d.close();
        out.close();
    }
}
