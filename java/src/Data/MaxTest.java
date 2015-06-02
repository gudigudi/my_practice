package Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by gudi on 6/2/15.
 */
public class MaxTest {

    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter 2 ints on one line: ");

        String oneLine = null;
        try {
            oneLine = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (oneLine == null)
            return;

        Scanner str = new Scanner(oneLine);

        int x = str.nextInt();
        int y = str.nextInt();

        System.out.println("Max: " + Math.max(x, y));
    }
}
