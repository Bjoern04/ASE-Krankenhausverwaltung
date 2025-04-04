package de.dhbw.mixin.entities.examination;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ExaminationMixin.class)
public abstract class ExaminationMixin {
    public ExaminationMixin() {
    }

    public ExaminationMixin(@JsonProperty("id") final int id, @JsonProperty("examinationType") final String examinationType, @JsonProperty("examinationDate") final String examinationDate, @JsonProperty("examinationTime") final String examinationTime, @JsonProperty("doctorName") final String doctorName) {

    }
}
