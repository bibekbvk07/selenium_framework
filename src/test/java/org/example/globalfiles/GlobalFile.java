package org.example.globalfiles;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GlobalFile {
    public static String globalWindowHandle;
    public static List<WebElement> nameList;

    public static int index;
    public static boolean isChecked;
    public static JSONObject credential;

    public static String ACCESS_TOKEN;

    public static String extractDigitsInParentheses(String inputString) {
        // Using a regular expression to match digits inside parentheses and extracting them
        String regex = "\\((\\d+)\\)";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(inputString);

        // Check if there is a match and extract the digits
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return ""; // Return an empty string if no digits inside parentheses are found
        }
    }
}
