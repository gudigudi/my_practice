package java_tutorial;

import java.lang.System;

public class Interest {
    public static void main(String[] args) {
        double principal;
        double rate;
        double interest;

        principal = 17000;
        rate = 0.027;
        interest = principal * rate;

        principal += interest;

        System.out.println("The interest earned is $" + interest);
        System.out.println("The value of the investment after one year is $" + principal);
    }
}
