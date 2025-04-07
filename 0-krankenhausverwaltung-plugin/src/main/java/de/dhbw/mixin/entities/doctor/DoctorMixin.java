package de.dhbw.mixin.entities.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.doctor.entity.Doctor;

import java.util.List;
import java.util.UUID;

@JsonDeserialize(builder = Doctor.DoctorBuilder.class)
public abstract class DoctorMixin {

}
