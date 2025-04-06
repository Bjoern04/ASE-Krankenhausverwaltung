package de.dhbw.shared;

import de.dhbw.shared.util.LocationNumberValidator;

import java.util.Objects;

/**
 * Value Object for Room Address according to DDD principles.
 */
public final class RoomAddress {
    private final String building;
    private final String floor;
    private final String roomNumber;

    /**
     * Constructor for the RoomAddress Value Object.
     *
     * @param building The building number
     * @param floor The floor number
     * @param roomNumber The room number
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public RoomAddress(final String building, final String floor, final String roomNumber) {
        if (!LocationNumberValidator.isValidLocationNumber(building)) {
            throw new IllegalArgumentException("Building number must be a positive number.");
        }
        if (!LocationNumberValidator.isValidLocationNumber(floor)) {
            throw new IllegalArgumentException("Floor number must be a positive number or zero.");
        }
        if (!LocationNumberValidator.isValidRoomNumber(roomNumber)) {
            throw new IllegalArgumentException("Room number must be a positive number.");
        }

        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
    }

    /**
     * Returns the building number.
     *
     * @return The building number
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Returns the floor number.
     *
     * @return The floor number
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Returns the room number.
     *
     * @return The room number
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Checks if two RoomAddress objects are equal based on their values.
     *
     * @param o The object to compare with
     * @return true if both RoomAddress objects have the same values, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomAddress that = (RoomAddress) o;
        return Objects.equals(building, that.building) &&
                Objects.equals(floor, that.floor) &&
                Objects.equals(roomNumber, that.roomNumber);
    }

    /**
     * Calculates the hashCode based on the values.
     *
     * @return The hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(building, floor, roomNumber);
    }

    /**
     * Returns a string representation of the RoomAddress object.
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "RoomAddress{" +
                "building=" + building +
                ", floor=" + floor +
                ", roomNumber=" + roomNumber +
                '}';
    }
}
