package de.dhbw.valueobjects;

import java.util.Objects;

/**
 * Value Object for Room Address according to DDD principles.
 */
public final class RoomAddress {
    private final int building;
    private final int floor;
    private final int roomNumber;

    /**
     * Constructor for the RoomAddress Value Object.
     *
     * @param building The building number
     * @param floor The floor number
     * @param roomNumber The room number
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public RoomAddress(final int building, final int floor, final int roomNumber) {
        if (building <= 0) {
            throw new IllegalArgumentException("Building number must be positive");
        }
        if (floor < 0) {
            throw new IllegalArgumentException("Floor number cannot be negative");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be positive");
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
    public int getBuilding() {
        return building;
    }

    /**
     * Returns the floor number.
     *
     * @return The floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Returns the room number.
     *
     * @return The room number
     */
    public int getRoomNumber() {
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
        return building == that.building &&
                floor == that.floor &&
                roomNumber == that.roomNumber;
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
