package de.dhbw.mixin.value_objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AddressMixin {
    @JsonCreator
    public AddressMixin(@JsonProperty("street") final String street,  @JsonProperty("houseNumber") final String houseNumber, @JsonProperty("zipCode") final String zipCode, @JsonProperty("city") final String city){

    }
}
