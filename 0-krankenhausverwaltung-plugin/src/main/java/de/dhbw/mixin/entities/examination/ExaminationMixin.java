package de.dhbw.mixin.entities.examination;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.shared.ExaminationType;

import java.time.LocalDateTime;
import java.util.UUID;


public abstract class ExaminationMixin {
    @JsonCreator
    public ExaminationMixin(@JsonProperty("id") final int id, @JsonProperty("examinationType") final ExaminationType examinationType, @JsonProperty("startTime") final LocalDateTime startTime, @JsonProperty("endTime") final LocalDateTime endTime, @JsonProperty("patientId") final UUID patientId, @JsonProperty("doctorId") final UUID doctorId) {
        // Constructor for Jackson

    }
}
