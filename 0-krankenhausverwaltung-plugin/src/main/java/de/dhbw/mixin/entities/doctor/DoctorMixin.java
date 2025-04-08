package de.dhbw.mixin.entities.doctor;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.aggregates.doctor.entity.Doctor;

@JsonDeserialize(builder = Doctor.DoctorBuilder.class)
public abstract class DoctorMixin {

}
