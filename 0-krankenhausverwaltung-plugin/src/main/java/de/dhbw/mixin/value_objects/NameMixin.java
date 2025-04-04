package de.dhbw.mixin.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NameMixin {
    @JsonCreator
    public NameMixin(@JsonProperty("firstName") final String firstName, @JsonProperty("lastName") final String lastName) {

    }
}
