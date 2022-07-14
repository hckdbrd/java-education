package com.knubisoft.base.arrays;

public class ArraysTasksImpl implements ArraysTasks {

    @Override
    public int[] reverse(int[] array) {

        int lastIndex = array.length-1;
        int[] reversedArray = new int[array.length];
        for (int i = 0; lastIndex >= i; i++) {
            reversedArray[i] = array[lastIndex-i];
        }

        return reversedArray;

    }

    @Override
    public int[] mergeArrays(int[] array1, int[] array2) {

        int arraySize = array1.length + array2.length;
        int[] mergedArray = new int[arraySize];
        int cursor = 0;

        for (int i : array1) {
            mergedArray[cursor] = i;
            cursor++;
        }

        for (int i : array2) {
            mergedArray[cursor] = i;
            cursor++;
        }

        return mergedArray;
    }

    @Override
    public int[] findMax3InArray(int[] array) {

        int[] result;
        int[] max3InArray = new int[3];

        if (array.length < 3) {
            result = array;
        } else {
            int max1 = max3InArray[0], max2 = max3InArray[1], max3 = max3InArray[2];
            for (int e : array) {
                if (e > max1) {
                    max1 = e;
                    max3InArray[0] = max1;
                }
            }
            for (int e: array) {
                if (e > max2 && max1 > e) {
                    max2 = e;
                    max3InArray[1] = max2;
                }
            }
            for (int e: array) {
                if (e > max3 && max2 > e) {
                    max3 = e;
                    max3InArray[2] = max3;
                }
            }
            result = max3InArray;
        }

        return result;
    }

    @Override
    public int findLongestIncreasingContinuesSubsequence(int[] array) {

        int maxSequence = 0;
        int counter = 1;

        for (int i = 0; array.length-1 > i; i++) {
            if (array[i] < array[i+1]) {
                counter++;
            } else {
                counter = 1;
            }
            if (counter > maxSequence) maxSequence = counter;
        }

        return maxSequence;
    }

    @Override
    public int sumOfAllUniqueElements(int[] array) {
        return 0;
    }

    @Override
    public int[] moveZeroes(int[] array) {

        int currentElement;
        int previousElement;

        for (int i = 0; array.length > i; i++) {
            if (array[i] != 0 && i != 0) {
                currentElement = array[i];
                previousElement = array[i-1];
                while ( i > 0 ) {
                    if (array[i-1] == 0) {
                        array[i-1] = currentElement;
                        array[i] = previousElement;
                        i--;
                    } else {
                        break;
                    }
                }
            }
        }
        return array;
    }

    @Override
    public int findFinalValue(int[] nums, int original) {
        int finalValue;

        for (int i = 0; nums.length > i;) {
            if (nums[i] == original) {
                original *= 2;
                i = 0;
            } else {
                i++;
            }
        }
        finalValue = original;

        return finalValue;
    }

    @Override
    public String longestCommonPrefix(String[] words) {

        if (words.length == 0) {
            return "";
        }

        String prefixBuilder = "";
        String basePrefix = words[0];

        for (String word: words) {
            if ( word.length() < basePrefix.length() ) {
                basePrefix = word;
            }
        }

        outer:
        for (int i = 0; i < basePrefix.length(); i++) {

            for (String word : words) {

                char baseChar = basePrefix.charAt(i);
                char switchChar = word.charAt(i);

                if (baseChar != switchChar) break outer;
            }
            prefixBuilder += basePrefix.charAt(i);
        }

        System.out.println(prefixBuilder);
        return prefixBuilder;
    }

    @Override
    public int missingNumber(int[] array) {
        return -1;
    }

    @Override
    public boolean containsDuplicate(int[] array) {

        boolean isDuplicate = false;

        for (int i = 0; i < array.length; i++) {

            int uniqueElement = array[i];

            for (int j = 0; j < array.length; j++) {
                if (uniqueElement == array[j] && i != j) {
                    isDuplicate = true;
                    break;
                }
            }
        }

        return isDuplicate;
    }
}
