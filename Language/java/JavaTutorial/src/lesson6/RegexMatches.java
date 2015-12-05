package lesson6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
    private static final String REGEX = "\\bcat\\b";
    private static final String INPUT = "cat cat cat cattie cat";

    public static void main(String[] args) {
        String line = "This order was placed for QT3000, OK?";
        String pattern = "(.*)(\\d+)(.*)";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        if (m.find()){
            System.out.println("Found value : " + m.group(0));
            System.out.println("Found value : " + m.group(1));
            System.out.println("Found value : " + m.group(2));
        }else {
            System.out.println("NO MATCH");
        }

        Pattern pattern1 = Pattern.compile(REGEX);
        Matcher matcher = pattern1.matcher(INPUT);
        int count = 0;
        while (matcher.find()){
            count++;
            System.out.println("Match number " + count);
            System.out.println("start() : " + m.start());
            System.out.println("end() : " + m.end());
        }
    }
}
