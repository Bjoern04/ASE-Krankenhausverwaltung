package de.dhbw.mixin.entities.doctor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public abstract class DoctorBuilderMixin {
    @JsonCreator
    public DoctorBuilderMixin(@JsonProperty("id") UUID id, @JsonProperty("name") Name name, @JsonProperty("address") Address address, @JsonProperty("dateOfBirth") LocalDate dateOfBirth, @JsonProperty("contact") Contact contact) {
    }

    @JsonProperty("examinationIds")
    public abstract Doctor.DoctorBuilder withExaminationIds(List<UUID> examinationIds);

    @JsonProperty("examinationTypes")
    public abstract Doctor.DoctorBuilder withExaminationTypes(List<ExaminationType> examinationTypes);
}
