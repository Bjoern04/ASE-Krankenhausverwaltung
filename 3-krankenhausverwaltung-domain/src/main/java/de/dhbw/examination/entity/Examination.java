package de.dhbw.examination.entity;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.ExaminationType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Examination {
    // Fields
    private final UUID id;
    private ExaminationType examinationType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID patientId;
    private UUID doctorId;

    public Examination(UUID id, ExaminationType examinationType, LocalDateTime startTime, LocalDateTime endTime, UUID patientId, UUID doctorId) {
        if (id == null) {
            throw new NullPointerException("Examination ID must not be null.");
        }
        this.id = id;

        if (examinationType == null) {
            throw new NullPointerException("Examination type must not be null.");
        }
        this.examinationType = examinationType;

        if (startTime == null) {
            throw new NullPointerException("Start time of the examination must not be null.");
        }

        if (endTime == null) {
            throw new NullPointerException("End time of the examination must not be null.");
        }

        if (!examinationTimesAreValid(startTime, endTime)) {
            throw new IllegalArgumentException("End time of the examination must be after the start time.");
        }
        this.startTime = startTime;
        this.endTime = endTime;

        if (patientId == null) {
            throw new NullPointerException("Patient of the examination must not be null.");
        }
        this.patientId = patientId;

        if (doctorId == null) {
            throw new NullPointerException("Doctor of the examination must not be null.");
        }
        this.doctorId = doctorId;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public UUID getPatient() {
        return patientId;
    }

    public UUID getDoctor() {
        return doctorId;
    }


    public void updateExaminationType(ExaminationType examinationType) {
        if (examinationType == null) {
            throw new NullPointerException("Type of the examination must not be null.");
        }
        this.examinationType = examinationType;
    }

    public void updateStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            throw new NullPointerException("Start time of the examination must not be null.");
        }
        this.startTime = startTime;
    }

    public void updateEndTime(LocalDateTime endTime) {
        if (endTime == null) {
            throw new NullPointerException("End time of the examination must not be null.");
        }
        this.endTime = endTime;
    }

    public void updatePatient(UUID patient) {
        if (patient == null) {
            throw new NullPointerException("Patient of the examination must not be null.");
        }
        this.patientId = patient;
    }

    public void updateDoctor(UUID doctor) {
        if (doctor == null) {
            throw new NullPointerException("Doctor of the examination must not be null.");
        }
        this.doctorId = doctor;
    }

    public boolean examinationTimesAreValid(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isBefore(endTime);
    }
}
