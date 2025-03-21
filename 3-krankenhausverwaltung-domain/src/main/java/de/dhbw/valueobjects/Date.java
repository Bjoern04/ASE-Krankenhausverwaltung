package de.dhbw.valueobjects;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Value Object for Date according to DDD principles.
 */
public final class Date {
    private final int day;
    private final int month;
    private final int year;

    /**
     * Constructor for the Date Value Object.
     *
     * @param day The day of the month (1-31)
     * @param month The month (1-12)
     * @param year The year
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Date(final int day, final int month, final int year) {
        validateDate(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the day of the month.
     *
     * @return The day
     */
    public int getDay() {
        return day;
    }

    /**
     * Returns the month.
     *
     * @return The month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the year.
     *
     * @return The year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the date formatted as a birthday string.
     *
     * @return The formatted birthday string
     */
    public String getBirthday() {
        return day + "." + month + "." + year;
    }

    /**
     * Checks if two Date objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both Date objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Date date = (Date) o;
        return day == date.day &&
                month == date.month &&
                year == date.year;
    }

    /**
     * Calculates the hashCode based on the values.
     *
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    /**
     * Returns a string representation of the Date object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    /**
     * Validates that the provided date values form a valid date.
     *
     * @param day The day to validate
     * @param month The month to validate
     * @param year The year to validate
     * @throws IllegalArgumentException if the date is invalid
     */
    private void validateDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        if (day < 1) {
            throw new IllegalArgumentException("Day must be positive");
        }

        // Check if the day is valid for the given month and year
        try {
            LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date: " + e.getMessage());
        }
    }

    /**
     * Factory method to create a Date from a LocalDate.
     *
     * @param localDate The LocalDate to convert
     * @return A new Date value object
     */
    public static Date fromLocalDate(LocalDate localDate) {
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    /**
     * Converts this Date to a LocalDate.
     *
     * @return The LocalDate representation
     */
    public LocalDate toLocalDate() {
        return LocalDate.of(year, month, day);
    }
}