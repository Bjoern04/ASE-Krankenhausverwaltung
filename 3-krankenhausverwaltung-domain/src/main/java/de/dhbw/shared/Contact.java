package de.dhbw.shared;

import java.util.Objects;

/**
 * Value Object for Contact according to DDD principles.
 */
public final class Contact {
    private final int phoneNumber;
    private final String email;

    /**
     * Constructor for the Contact Value Object.
     *
     * @param phoneNumber The phone number
     * @param email The email address
     * @throws IllegalArgumentException if email is null, empty, or invalid
     */
    public Contact(final int phoneNumber, final String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Returns the phone number.
     *
     * @return The phone number
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address.
     *
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Checks if two Contact objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both Contact objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;
        return phoneNumber == contact.phoneNumber &&
                Objects.equals(email, contact.email);
    }

    /**
     * Calculates the hashCode based on the values.
     *
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, email);
    }

    /**
     * Returns a string representation of the Contact object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "Contact{" +
                "phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Validates email format using a simple check.
     *
     * @param email The email to validate
     * @return True if the email format is valid, otherwise false
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
