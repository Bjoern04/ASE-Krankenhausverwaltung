package de.dhbw.shared;

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
        if (houseNumber == null || !validateHouseNumber(houseNumber)) {
            throw new IllegalArgumentException("Die Hausnummer muss eine Zahl sein und positiv.");
        }
        if (zipCode == null || !validateZipCode(zipCode)) {
            throw new IllegalArgumentException("Die PLZ muss eine Zahl sein und positiv.");
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
        return houseNumber == address.houseNumber &&
                zipCode == address.zipCode &&
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

    /**
     * Returns a formatted address string.
     *
     * @return Formatted address string
     */
    public String getFormattedAddress() {
        return street + " " + houseNumber + ", " + zipCode + " " + city;
    }

    private boolean validateHouseNumber(String houseNumber) {
        try {
            int number = Integer.parseInt(houseNumber);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateZipCode(String zipCode) {
        try {
            int number = Integer.parseInt(zipCode);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
