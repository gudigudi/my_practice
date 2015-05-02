

import java.util.ArrayList;
import java.security.*;

public class LearnJava {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        System.out.println("Integer: " + 10 +
                           " Double: " + 3.14 +
                           " Boolean: " + true);

        byte fooByte = 100;

        short fooShort = 10000;

        int fooInt = 1;

        long fooLong = 100000L;

        float fooFloat = 234.5f;

        double fooDouble = 123.4;

        boolean fooBoolean = true;
        boolean barBoolean = false;

        char fooChar = 'A';

        final int HOURS_I_WORK_PER_WEEK = 9001;

        String fooString = "My String Is Here!";
        String barString = "Printing on a new line?\nNo Problem!";
        String bazString = "Do you want to add a tab?\tNo Problem!";
        System.out.println(fooString);
        System.out.println(barString);
        System.out.println(bazString);

        int[] intArray = new int[10];
        String[] stringArray = new String[1];
        boolean[] booleanArray = new boolean[100];

        int[] y = {9000, 1000, 1337};

        System.out.println("intArray @ 0: " + intArray[0]);

        intArray[1] = 1;
        System.out.println("intArray @ 1: " + intArray[1]);

        /**
         * new Class
         */
        Bicycle trek = new Bicycle();
        trek.speedUp(3);
        trek.setCadence(100);
        System.out.println("trek info: " + trek.toString());
    }
}
