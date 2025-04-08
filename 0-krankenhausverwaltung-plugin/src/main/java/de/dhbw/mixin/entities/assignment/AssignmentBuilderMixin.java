package de.dhbw.mixin.entities.assignment;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.aggregates.assignment.entity.Assignment;

import java.time.LocalDate;
import java.util.UUID;

public abstract class AssignmentBuilderMixin {
    public AssignmentBuilderMixin (@JsonProperty("id") UUID id, @JsonProperty("roomId") UUID room, @JsonProperty("patientId") UUID patient, @JsonProperty("dateOfAdmission") LocalDate dateOfAdmission) {

    }

    @JsonProperty("dateOfDischarge")
    public abstract Assignment.AssignmentBuilder withDateOfDischarge (LocalDate dateOfDischarge);
}
