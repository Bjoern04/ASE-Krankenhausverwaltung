package de.dhbw.mixin.entities.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.aggregates.patient.entity.Patient;

@JsonDeserialize(builder = Patient.PatientBuilder.class)
    public abstract class PatientMixin {
}
