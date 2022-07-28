package com.knubisoft.base.pattern;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTasksImpl implements PatternTasks {

    @Override
    public boolean haveSetOfCharacters(String text) {
        if (text == null || text.equals(" ") || text.equals("")) throw new IllegalArgumentException();
        int textLength = text.length();
        return text.matches(String.format("\\w{%d}",textLength));
    }

    @Override
    public String matchByCharacters(String text) {
        if (text ==  null) throw new IllegalArgumentException();

        if (text.matches("pq*")) {
            return "Found a match!";
        } else {
            return "Not matched!";
        }
    }

    @Override
    public String matchStartAndEnd(String text) {
        if (text == null) throw new IllegalArgumentException();

        Pattern pattern = Pattern.compile("\\b[^Gg\\s]\\w*[Gg]\\w*\\w[^Gg\\s]\\b");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("Found a match!");
            return "Found a match!";
        } else {
            System.out.println("Not matched!");
            return "Not matched!";
        }
    }

    @Override
    public String matchIpAddress(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.replaceAll("\\b(0+)(\\d*)\\b","$2");
    }

    @Override
    public String matchVowels(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.replaceAll("(?i)[aeiou]","");
    }

    @Override
    public boolean validatePIN(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.matches("^\\d{4}$|^\\d{6}$|^\\d{8}$");
    }

    @Override
    public String divideDigit(int digit) {
        String digitString = Integer.valueOf(digit).toString();
        char[] digitStringArray = digitString.toCharArray();
        int lastIndexOfDigitStringArray = digitStringArray.length-1;
        char[] reversedDigitStringArray = new char[digitStringArray.length];

        for (int i = 0; i < digitStringArray.length ; i++) {
            reversedDigitStringArray[i] = digitStringArray[lastIndexOfDigitStringArray-i];
        }

        String reversedDigitString = String.valueOf(reversedDigitStringArray).replaceAll("0{3}","000#");

        digitStringArray = reversedDigitString.toCharArray();
        lastIndexOfDigitStringArray = digitStringArray.length-1;
        reversedDigitStringArray = new char[digitStringArray.length];

        for (int i = 0; i < digitStringArray.length ; i++) {
            reversedDigitStringArray[i] = digitStringArray[lastIndexOfDigitStringArray-i];
        }

        return String.valueOf(reversedDigitStringArray);
    }

    @Override
    public String removeAllNonAlphanumericCharacters(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.replaceAll("\\W","");
    }

    @Override
    public boolean validatePhoneNumber(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.matches("(\\d{10})|(\\(\\d{3}\\)\\d{3}\\-\\d{4})|(\\(\\d{3}\\)\\d{7})|(\\d{3}\\-\\d{3}\\-\\d{4})");
    }

    @Override
    public String getLastVowelsByConstraint(String text, int n) {
        if (
                ( text == null || text.equals("") || text.equals(" ") )
                        ||
                        ( n < 1 )
        ) throw new IllegalArgumentException();
        text = text.replaceAll("[^euioa]+", "");

        int startIndex = text.length() - n;

        return text.substring(startIndex);
    }

    @Override
    public boolean isMathematicalExpression(String text) {
        if ( text == null || text.equals("") || text.equals(" ") ) throw new IllegalArgumentException();
        int textLength = text.length();
        return text.matches(String.format("\\d{%d}|(^\\s?\\d+\\s*(\\s*[-+*/]\\s*\\d+\\s*)+)", textLength));
    }

    @Override
    public String insertDash(String text) {
        if (text == null || text.equals("") || text.equals(" ")) throw new IllegalArgumentException();
        return text.replaceAll("([A-Z])","$1-");
    }
}
