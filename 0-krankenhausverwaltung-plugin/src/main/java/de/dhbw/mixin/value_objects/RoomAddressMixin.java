package de.dhbw.mixin.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RoomAddressMixin {
    public RoomAddressMixin(@JsonProperty("building") final int building, @JsonProperty("floor") final int floor, @JsonProperty("roomNumber") final int roomNumber) {

    }
}
