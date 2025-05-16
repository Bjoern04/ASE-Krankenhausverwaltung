package de.dhbw.shared.util;

public final class NumberValidator {
    private NumberValidator() {
    }

    public static boolean isValidNumberBiggerZero(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidNumberBiggerOrEqualZero(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
