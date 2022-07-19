package com.knubisoft.base.numbers;

import java.math.BigInteger;
import java.util.Arrays;

public class NumbersTasksImpl implements NumbersTasks {

    @Override
    public void swapTwoNumbersWithoutUsingTemporaryVariable(int firstNumber, int secondNumber) {

        firstNumber = firstNumber + secondNumber;
        secondNumber = firstNumber - secondNumber;
        firstNumber = firstNumber - secondNumber;

        System.out.println("1-st: "+firstNumber+"2-nd: "+secondNumber);
    }

    @Override
    public boolean isUglyInt(int number) {

        boolean isUgly = number == 1;

        while ( number > 1 ) {
            if (number % 2 == 0) {
                number /= 2;
                if (number == 1) {
                    isUgly = true;
                    break;
                }
                continue;
            }
            if (number % 3 == 0) {
                number /= 3;
                if (number == 1) {
                    isUgly = true;
                    break;
                }
                continue;
            }
            if (number % 5 == 0) {
                number /= 5;
                if (number == 1) {
                    isUgly = true;
                    break;
                }
            } else {
                break;
            }
        }
        System.out.println(isUgly);
        return isUgly;
    }

    @Override
    public int addDigits(int number) {

        int numberLength = Integer.toString(number).length();

        while (numberLength > 1) {
            char[] numArray = Integer.toString(number).toCharArray();
            number = 0;
            for (char character : numArray) {
                number += Character.getNumericValue(character);
                numberLength = Integer.toString(number).length();
            }
        }
        return number;
    }

    @Override
    public boolean isHarshadNumber(int number) {

        if (number == 0) return false;

        boolean isHarshad = false;

        int divisor = 0;
        char[] numArray = Integer.toString(number).toCharArray();

        for (char character : numArray) {
            divisor += Character.getNumericValue(character);
        }

        if (number % divisor == 0) isHarshad = true;

        return isHarshad;
    }

    @Override
    public boolean isPrime(int number) {

        if (number > 1) {
            return number % 2 != 0
                    &&
                    number % 3 != 0
                    &&
                    number % 5 != 0
                    &&
                    number % 7 != 0;
        } else {
            return false;
        }

    }

    @Override
    public boolean isArmstrongNumber(int number) {

        int numberLength = Integer.toString(number).length();
        char[] array = Integer.toString(number).toCharArray();

        int result = 0;

        for (char element : array) {
            int num = Character.getNumericValue(element);
            int temp = num;
            for (int i = 0; i < numberLength-1; i++) {
                num *= temp;
            }
            result += num;
        }

        return number == result;
    }

    @Override
    public BigInteger factorial(int number) {

        BigInteger result = BigInteger.ONE;

        for ( BigInteger i = result; i.intValue() <= number; i = i.add(BigInteger.ONE) ) {
            result = result.multiply(i);
        }

        return result;
    }

    @Override
    public boolean palindrome(int number) {

        char[] array = Integer.toString(number).toCharArray();
        int lastIndex = array.length-1;

        char[] reversedArray = new char[array.length];
        for (int i = 0; i <= lastIndex; i++) {
            reversedArray[i] = array[lastIndex-i];
        }

        return Arrays.equals(array,reversedArray);
    }

    @Override
    public boolean isAutomorphic(int number) {

        BigInteger thisNumber = BigInteger.valueOf(number);
        String Sequence = Integer.toString(number);
        BigInteger square = thisNumber.multiply(thisNumber);

        String SquareSequence = square.toString();
        String SquareSequenceEnding = SquareSequence.substring(SquareSequence.length() - Sequence.length());

        return Sequence.equals(SquareSequenceEnding);
    }
}
