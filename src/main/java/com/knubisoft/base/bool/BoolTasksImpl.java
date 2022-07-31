package com.knubisoft.base.bool;

import lombok.Value;

import java.util.Objects;

public class BoolTasksImpl implements BoolTasks {

    @Override
    public Boolean isTrueAutobox(boolean value) {
        return (Boolean) value;
    }

    @Override
    public Boolean isFalseAutobox(boolean value) {
        return (Boolean) value;
    }

    @Override
    public boolean isTrueUnbox(Boolean value) {
        return (boolean) value;
    }

    @Override
    public boolean isFalseUnbox(Boolean value) {
        return (boolean) value;
    }

    @Override
    public boolean andFunction(int digit, String string) {
        if ( string != null && !string.equals("") && !string.equals(" ")) {
            int stringLength = string.length();
            return string.matches(String.format("\\d{%d}", stringLength))
                   &&
                   digit == Integer.parseInt(string);
        } else {
            return false;
        }
    }

    @Override
    public boolean orFunction(int digit, String string) {
        if ( string == null || string.equals("") || string.equals(" ")) {
            return false;
        } else {
            int stringLength = string.length();
            if(string.matches(String.format("\\d{%d}", stringLength))) {
                return digit == Integer.parseInt(string);
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean andComplexFunction(int generatedFirstDigit, double generatedSecondDigit, int range) {
        if ( range >= 3 && range <= 5 ) {
            int secondDigit =
                    ( (generatedSecondDigit * 100) % 100 >= 50.0 ) ?
                    (int) Math.ceil(generatedSecondDigit) :
                    (int) generatedSecondDigit;
            if (generatedFirstDigit > range && secondDigit > range) return false;
            return generatedFirstDigit == secondDigit;
        } else {
            return false;
        }
    }

    @Override
    public boolean orComplexFunction(int generatedFirstDigit, double generatedSecondDigit, double generatedThirdDigit, int range) {
        if ( range == 5 || range == 6 || range == 7 ) {
            int secondDigit =
                    ( (generatedSecondDigit * 100) % 100 >= 50.0 ) ?
                    (int) Math.ceil(generatedSecondDigit) :
                    (int) generatedSecondDigit;

            int thirdDigit =
                    ( (generatedThirdDigit * 100) % 100 >= 50.0 ) ?
                            (int) Math.ceil(generatedThirdDigit) :
                            (int) generatedThirdDigit;

            return generatedFirstDigit == secondDigit || generatedFirstDigit == thirdDigit;
        } else {
            return false;
        }
    }

    @Override
    public boolean isSameSizeArray(Object[] firstArray, Object... secondArray) {
        if (firstArray == null || secondArray == null) return false;
        return firstArray.length == secondArray.length;
    }

    @Override
    public boolean isSameCharactersCount(String username, String name, int maxLength) {
        if (maxLength < 1
                || Objects.equals(username, "") || Objects.equals(name, "")
                || Objects.equals(username, " ") || Objects.equals(name, " ")
                || username == null || name == null)
            return false;

        return username.length() <= maxLength && name.length() <= maxLength;
    }
}
