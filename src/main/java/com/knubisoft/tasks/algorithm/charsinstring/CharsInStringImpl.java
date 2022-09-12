package com.knubisoft.tasks.algorithm.charsinstring;

import java.util.HashMap;
import java.util.Map;

public class CharsInStringImpl implements CharsInString {
    @Override
    public Map<Character, Integer> charactersCount(String text) {
        Map<Character, Integer> charMap = new HashMap<>();
        char[] charArray = text.toCharArray();

        for (char ch : charArray) {
            if (String.valueOf(ch).equals(" ")) continue;
            if (!charMap.containsKey(ch)) {
                charMap.put(ch, 1);
            } else {
                int value = charMap.get(ch);
                charMap.put(ch, value+1);
            }
        }
        return charMap;
    }
}
