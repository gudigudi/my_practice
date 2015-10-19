package com.company.Multithreading;

/**
 * Created by gudi on 4/16/15.
 */
public class TestThread2 {

    public static void main(String[] args) {
        ThreadDemo T1 = new ThreadDemo("Thread-1");
        T1.start();

        ThreadDemo T2 = new ThreadDemo("Thread-1");
        T2.start();
    }
}
