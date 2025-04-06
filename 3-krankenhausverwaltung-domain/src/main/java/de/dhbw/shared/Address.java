package de.dhbw.shared;

import de.dhbw.shared.util.LocationNumberValidator;

import java.util.Objects;

/**
 * Value Object for Address according to DDD principles.
 */
public final class Address {
    private final String street;
    private final String houseNumber;
    private final String zipCode;
    private final String city;

    /**
     * Constructor for the Address Value Object.
     *
     * @param street The street name
     * @param city The city name
     * @param houseNumber The house number
     * @param zipCode The postal/zip code
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Address(final String street, final String houseNumber, final String zipCode, final String city) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street must not be null or empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City must not be null or empty");
        }
        if (houseNumber == null || !LocationNumberValidator.isValidLocationNumber(houseNumber)) {
            throw new IllegalArgumentException("House number must be a positive number.");
        }
        if (zipCode == null || !LocationNumberValidator.isValidLocationNumber(zipCode)) {
            throw new IllegalArgumentException("ZIP code must be a positive number.");
        }

        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
    }

    /**
     * Returns the street name.
     *
     * @return The street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Returns the city name.
     *
     * @return The city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the house number.
     *
     * @return The house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Returns the ZIP code.
     *
     * @return The ZIP code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Checks if two Address objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both Address objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;
        return Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city);
    }

    /**
     * Calculates the hashCode based on the values.
     *
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(street, city, houseNumber, zipCode);
    }

    /**
     * Returns a string representation of the Address object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber=" + houseNumber +
                ", zipCode=" + zipCode +
                '}';
    }
}
