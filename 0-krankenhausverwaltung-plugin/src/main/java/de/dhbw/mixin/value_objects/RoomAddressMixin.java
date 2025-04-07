package de.dhbw.mixin.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class RoomAddressMixin {
    public RoomAddressMixin(@JsonProperty("building") final String building, @JsonProperty("floor") final String floor, @JsonProperty("roomNumber") final String roomNumber) {

    }
}
