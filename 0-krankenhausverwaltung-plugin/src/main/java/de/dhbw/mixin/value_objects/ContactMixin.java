package de.dhbw.mixin.value_objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ContactMixin {
    public ContactMixin(@JsonProperty("phoneNumber") final String phoneNumber, @JsonProperty("email") final String email){

    }
}
