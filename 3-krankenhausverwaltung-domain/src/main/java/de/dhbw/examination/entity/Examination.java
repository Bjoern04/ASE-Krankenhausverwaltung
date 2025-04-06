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
            throw new NullPointerException("Untersuchungs-ID darf nicht null sein.");
        }
        this.id = id;

        if (examinationType == null) {
            throw new NullPointerException("Typ der Untersuchung darf nicht null sein.");
        }
        this.examinationType = examinationType;

        if (startTime == null) {
            throw new NullPointerException("Startzeit der Untersuchung darf nicht null sein.");
        }
        this.startTime = startTime;

        if (endTime == null) {
            throw new NullPointerException("Endzeitpunkt der Untersuchung darf nicht null sein.");
        }
        this.endTime = endTime;

        if (patientId == null) {
            throw new NullPointerException("Patient der an Untersuchung teilnimmt darf nicht null sein.");
        }
        this.patientId = patientId;

        if (doctorId == null) {
            throw new NullPointerException("Arzt der Untersuchung durchführt darf nicht null sein.");
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
            throw new NullPointerException("Typ der Untersuchung darf nicht null sein.");
        }
        this.examinationType = examinationType;
    }

    public void updateStartTime(LocalDateTime startTime) {
        if (startTime == null) {
            throw new NullPointerException("Startzeit der Untersuchung darf nicht null sein.");
        }
        this.startTime = startTime;
    }

    public void updateEndTime(LocalDateTime endTime) {
        if (endTime == null) {
            throw new NullPointerException("Endzeitpunkt der Untersuchung darf nicht null sein.");
        }
        this.endTime = endTime;
    }

    public void updatePatient(UUID patient) {
        if (patient == null) {
            throw new NullPointerException("Patient der an Untersuchung teilnimmt darf nicht null sein.");
        }
        this.patientId = patient;
    }

    public void updateDoctor(UUID doctor) {
        if (doctor == null) {
            throw new NullPointerException("Arzt der Untersuchung durchführt darf nicht null sein.");
        }
        this.doctorId = doctor;
    }
}
