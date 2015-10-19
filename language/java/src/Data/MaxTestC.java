package Data;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by gudi on 6/2/15.
 */
public class MaxTestC {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter 2 ints on one line: ");

        try {
            String oneLine = in.nextLine();
            Scanner str = new Scanner(oneLine);

            int x = str.nextInt();
            int y = str.nextInt();

            System.out.println("Max: " + Math.max(x, y));
        } catch (NoSuchElementException e) {
            System.err.println("Error: need two ints");
        }
    }
}
