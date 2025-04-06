package de.dhbw.shared.util;

public final class LocationNumberValidator {
    private LocationNumberValidator() {
    }

    public static boolean isValidLocationNumber(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidRoomNumber(String numberString) {
        try {
            int number = Integer.parseInt(numberString);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
