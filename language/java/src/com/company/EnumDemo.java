package com.company;

/**
 * Created by gudi on 4/11/15.
 */
public class EnumDemo {
    enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY };
    enum Month { JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC };

    public static void main(String [] args) {
        Day tgif;
        Month libra;

        tgif = Day.FRIDAY;
        libra = Month.OCT;

        System.out.println("My sign is libra, since I was born in " + libra);
        System.out.println("That's the " + libra.ordinal());
        System.out.println("-th month of the year.");
        System.out.println("    (Counting from 0, of cource!)");

        System.out.println("Is't it nice to get to " + tgif);
    }
}
