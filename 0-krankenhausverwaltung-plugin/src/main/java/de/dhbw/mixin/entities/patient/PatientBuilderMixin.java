package de.dhbw.mixin.entities.patient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public abstract class PatientBuilderMixin {
    @JsonCreator
    public PatientBuilderMixin(@JsonProperty("id") UUID id) {
        // Konstruktor-Body bleibt leer, da nur die Annotationen benötigt werden
    }

    @JsonProperty("name")
    public abstract Patient.PatientBuilder withName(Name name);

    @JsonProperty("address")
    public abstract Patient.PatientBuilder withAddress(Address address);

    @JsonProperty("dateOfBirth")
    public abstract Patient.PatientBuilder withDateOfBirth(LocalDate dateOfBirth);

    @JsonProperty("contact")
    public abstract Patient.PatientBuilder withContact(Contact contact);

    @JsonProperty("assignmentId")
    public abstract Patient.PatientBuilder withAssignment(UUID assignmentId);
}
