package de.dhbw.mixin.entities.doctor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.doctor.entity.Doctor;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public abstract class DoctorBuilderMixin {
    @JsonCreator
    public DoctorBuilderMixin(@JsonProperty("id") UUID id, @JsonProperty("name") Name name, @JsonProperty("address") Address address, @JsonProperty("contact") Contact contact) {
    }

    @JsonProperty("examinationIds")
    public abstract Doctor.DoctorBuilder withExaminationIds(List<UUID> examinationIds);

    @JsonProperty("dateOfBirth")
    public abstract Doctor.DoctorBuilder withDateOfBirth(LocalDate dateOfBirth);
}
