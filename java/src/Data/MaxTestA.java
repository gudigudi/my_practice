package Data;

import java.util.Scanner;

/**
 * Created by gudi on 6/1/15.
 */
public class MaxTestA {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int x, y;

        System.out.println("Enter 2 ints: ");

        if (in.hasNextInt()) {
            x = in.nextInt();
            if (in.hasNextInt()) {
                y = in.nextInt();

                System.out.println("Max: " + Math.max(x, y));
            }
        }
    }
}
