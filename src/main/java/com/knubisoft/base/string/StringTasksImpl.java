package com.knubisoft.base.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class StringTasksImpl implements StringTasks {

    @Override
    public String reverseString(String original) throws IllegalArgumentException {

        if ( original == null ) throw new IllegalArgumentException();

        char[] charArray = original.toCharArray();

        int lastIndex = original.length()-1;
        char[] reversedCharArray = new char[charArray.length];
        for (int i = 0; lastIndex >= i; i++) {
            reversedCharArray[i] = charArray[lastIndex-i];
        }

        return String.valueOf(reversedCharArray);
    }

    @Override
    public String insertStringInMiddle(String original, String toInsert) throws IllegalArgumentException {

        if ( original == null || original.isEmpty() ) throw new IllegalArgumentException();
        if ( toInsert == null || toInsert.isEmpty() ) throw new IllegalArgumentException();

        int middleIndex = original.length() / 2;
        StringBuilder updatedString = new StringBuilder(original).insert(middleIndex, toInsert);

        return updatedString.toString();
    }

    @Override
    public String insertSymbolInString(String original, char toInsert, int position) {

        if ( original == null || original.isEmpty() ) throw new IllegalArgumentException();
        if ( position >= original.length() || position < 0 ) throw new IllegalArgumentException();

        StringBuilder updatedString = new StringBuilder(original).insert( position-1, toInsert);
        return updatedString.toString();
    }

    @Override
    public String appendToString(StringBuilder original, String toAppend) {
        try {
            if (original.toString().equals("") || toAppend == null) throw new NoSuchElementException();
            return original.append(toAppend).toString();
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isPalindrome(String palindrome) {

        char[] array = palindrome.toCharArray();
        int lastIndex = array.length-1;

        char[] reversedArray = new char[array.length];
        for (int i = 0; i <= lastIndex; i++) {
            reversedArray[i] = array[lastIndex-i];
        }

        return Arrays.equals(array,reversedArray);
    }

    @Override
    public boolean hasLowerCase(String str) {
        try {
            return Objects.equals(str, str.toLowerCase());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String uniqueCharacters(String str) {

        try {
            StringBuilder uniqueChars = new StringBuilder();
            int counter = 0;
            for (char i : str.toCharArray()) {
                for (char j : str.toCharArray()) {
                    if (Character.toLowerCase(i)==Character.toLowerCase(j)) counter++;
                    if (counter > 1) break;
                }
                if (counter == 1) uniqueChars.append(i);
                counter = 0;
            }
            return uniqueChars.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String removeAllCharacters(String str, char charToRemove) {
        try {
            StringBuilder string = new StringBuilder(str);
            StringBuilder updatedString = new StringBuilder();
            for ( int i = 0; i < str.length(); i++) {
                if ( string.charAt(i) != charToRemove ) {
                    updatedString.append(string.charAt(i));
                }
            }
            return updatedString.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toCamelCase(String str) {

        try {
            if (Objects.equals(str, "")) throw new IllegalArgumentException();

            StringBuilder string = new StringBuilder(str);

            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) == '_' || string.charAt(i) == '-' || string.charAt(i) == ' ') {
                    if ( string.charAt( i + 1 ) != Character.toUpperCase(string.charAt( i + 1 )) ) {
                        string.replace( i, i+2, Character.toString(Character.toUpperCase(string.charAt( i + 1 ))) );
                    } else {
                        string.replace( i, i+1, "" );
                    }
                    i = 0;
                }
            }
            return string.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getCountRepeatableString(String str) {
        if (str == null) throw new IllegalArgumentException();

        char[] charArray = str.toCharArray();
        StringBuilder numericString = new StringBuilder();
        int counter = 0;

        for (int i = 0; i < str.length(); i++){
            for (int j = i; j < str.length(); j++) {
                if (charArray[i] == charArray[j]) {
                    counter++;
                    if (counter > 9) counter = 1;
                    numericString.append(counter);
                }
            }
            counter = 0;
        }

        return numericString.toString();
    }

    @Override
    public String sortStringCharactersAsc(String str) {

        if (Objects.equals(str, null)) throw new IllegalArgumentException();

        StringBuilder string = new StringBuilder(str);
        for (int i = 0; i < string.length() - 1; i++) {

            if ( string.charAt(i) == ' ') string.deleteCharAt(i);

            if ( Character.toLowerCase(string.charAt(i)) > Character.toLowerCase(string.charAt(i+1)) ) {
                string.append(string.charAt(i));
                string.deleteCharAt(i);
                i = -1;
            }
        }

        return string.toString();
    }
}
