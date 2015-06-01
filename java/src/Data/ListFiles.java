package Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by gudi on 6/2/15.
 */
public class ListFiles {

    public static void main(String[] args) {

        if (args.length == 0)
            System.out.println("No files specified");
        for (String fileName : args)
            listFile(fileName);
    }

    private static void listFile(String fileName) {

        Scanner fileIn = null;
        System.out.println("FILE: " + fileName);

        try {
            fileIn = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (fileIn.hasNextLine()) {
            String oneLine = fileIn.nextLine();
            System.out.println(oneLine);
        }
        if (fileIn != null)
            fileIn.close();

    }
}
