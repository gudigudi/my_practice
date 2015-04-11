package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gudi on 4/11/15.
 */
public class RegexMatches {
    public static void main(String[] args) {
        String line = "This order was places for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
        } else {
            System.out.println("NO MATCH");
        }
    }
}
