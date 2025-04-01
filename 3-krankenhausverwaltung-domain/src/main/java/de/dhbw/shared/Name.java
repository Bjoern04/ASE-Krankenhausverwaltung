package de.dhbw.shared;

import java.util.Objects;

/**
 * Value Object for Name according to DDD principles.
 */
public final class Name {
    private final String firstName;
    private final String lastName;

    /**
     * Constructor for the Name Value Object.
     *
     * @param firstName The first name
     * @param lastName The last name
     * @throws IllegalArgumentException if any parameter is null or empty
     */
    public Name(final String firstName, final String lastName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name must not be null or empty");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name must not be null or empty");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the first name.
     *
     * @return The first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name.
     *
     * @return The last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name (firstName lastName).
     *
     * @return The full name
     */
    public String getFirstNameLastName() {
        return firstName + " " + lastName;
    }

    /**
     * Checks if two Name objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both Name objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) &&
                Objects.equals(lastName, name.lastName);
    }

    /**
     * Calculates the hashCode based on the values.
     *
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    /**
     * Returns a string representation of the Name object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
