package de.dhbw.shared;

import java.util.Objects;

/**
 * Value Object for Time according to DDD principles.
 */
public final class Time {
    private final int hour;
    private final int minute;

    /**
     * Constructs a Time Value Object.
     *
     * @param hour The hour (0-23).
     * @param minute The minute (0-59).
     * @throws IllegalArgumentException if hour or minute is out of range.
     */
    public Time(final int hour, final int minute) {
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23.");
        }
        if (minute < 0 || minute > 59) {
            throw new IllegalArgumentException("Minute must be between 0 and 59.");
        }
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Returns the hour.
     *
     * @return The hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns the minute.
     *
     * @return The minute.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Checks if two Time objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both Time objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time timeValue = (Time) o;
        return hour == timeValue.hour && minute == timeValue.minute;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    /**
     * Returns a string representation of the Time object.
     *
     * @return A string representation of the Time object.
     */
    @Override
    public String toString() {
        return hour + ":" + minute;
    }
}
