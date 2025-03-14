package exercises4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise4 {
    public static void main(String[] args) {
        String [] patterns = {
                "\\d+", //number
                "[a-zA-Z]+", //only letters and spaces
                "\\bJava\\b", //Java
                "^Hello.*", //starting with "Hello"
                ".*\\d{2,}.*", //two or more number
        };
        //Test string
        String [] testStrings = {
                "20905",
                "My name is Emily",
                "I am learning Java",
                "Hello, How are you?",
                "My birthday is July 27",
        };

        for (int i = 0; i<patterns.length; i++) {
            Pattern pattern = Pattern.compile(patterns[i]);
            Matcher positiveMatcher = pattern.matcher(testStrings[i]);
            Matcher negativeMatcher = pattern.matcher("XYZ");

            System.out.println("Pattern: " + patterns[i]);
            System.out.println("Positive Matches: " + testStrings[i] + "): " + positiveMatcher.find());
            System.out.println("Negative Matches (XYZ): " + negativeMatcher.find());
            System.out.println();
        }
    }
}
