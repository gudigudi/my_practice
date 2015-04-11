package com.company;

import java.util.Date;

/**
 * Created by gudi on 4/11/15.
 */
public class DiffDemo {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            System.out.println(new Date() + "\n");
            Thread.sleep(5 * 60 * 10);
            System.out.println(new Date() + "\n");
            long end = System.currentTimeMillis();
            long diff = end - start;
            System.out.println("Difference is : " + diff);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
