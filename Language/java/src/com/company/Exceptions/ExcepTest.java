package com.company.Exceptions;

/**
 * Created by gudi on 4/12/15.
 */
public class ExcepTest {
    public static void main(String[] args) {
        try {
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception throw :" + e);
        }
        System.out.println("Out of the block");
    }
}
