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
        return -1;
    }

    @Override
    public int[] moveZeroes(int[] array) {
        return null;
    }

    @Override
    public int findFinalValue(int[] nums, int original) {
        return -1;
    }

    @Override
    public String longestCommonPrefix(String[] words) {
        return null;
    }

    @Override
    public int missingNumber(int[] array) {
        return -1;
    }

    @Override
    public boolean containsDuplicate(int[] array) {
        return false;
    }
}
