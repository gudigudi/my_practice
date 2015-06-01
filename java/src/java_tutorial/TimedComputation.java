package java_tutorial;

import java.lang.System;
import java.util.Map;

public class TimedComputation {
    public static void main(String[] args) {
        long startTime, endTime;
        double time;

        startTime = System.currentTimeMillis();

        double width, height, hypotenuse;
        width = 42.0;
        height = 17.0;
        hypotenuse = Math.sqrt(width * width + height * height);
        System.out.println("A triangle with sides 42 and 17 has hypotenuse "
                + hypotenuse);

        System.out.println("\nMathematically, sin(x)*sin(x) + "
                + "cos(x)*cos(x) - 1 should be 0.");

        System.out.println("Let's check this for x = 1:");
        System.out.println("    sin(1)*sin(1) + cos(1)*cos(1) - 1 is "
                + (Math.sin(1)*Math.sin(1) + Math.cos(1)*Math.cos(1) - 1));

        System.out.println("(There can be round-off errors when computing with real numbers!)");
        System.out.println("\nHere is a random number:  " + Math.random());

        System.out.println("The value of Math.PI is " + Math.PI);

        endTime = System.currentTimeMillis();
        time = (endTime - startTime) / 1000.0;

        System.out.println("\nRun time in seconds was:  " + time);
    }
}
