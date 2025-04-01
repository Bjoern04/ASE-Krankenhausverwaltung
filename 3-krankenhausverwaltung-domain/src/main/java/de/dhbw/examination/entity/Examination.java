package de.dhbw.examination.entity;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.patient.entity.Patient;

import java.time.LocalDateTime;
import java.util.UUID;

public class Examination {
    // Fields
    private final UUID id;
    private String examinationType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Patient patient;
    private Doctor doctor;

    public Examination(UUID id, String examinationType, LocalDateTime startTime, LocalDateTime endTime, Patient patient, Doctor doctor) {
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

        if (patient == null) {
            throw new NullPointerException("Patient der an Untersuchung teilnimmt darf nicht null sein.");
        }
        this.patient = patient;

        if (doctor == null) {
            throw new NullPointerException("Arzt der Untersuchung durchführt darf nicht null sein.");
        }
        this.doctor = doctor;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getExaminationType() {
        return examinationType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }


    public void updateExaminationType(String examinationType) {
        if (examinationType == null) {
            throw new NullPointerException("Typ der Untersuchung darf nicht null sein.");
        }
        this.examinationType = examinationType;
    }

    public void updateStartTime(LocalDateTime startTime) {
        if (examinationType == null) {
            throw new NullPointerException("Startzeit der Untersuchung darf nicht null sein.");
        }
        this.startTime = startTime;
    }

    public void updateEndTime(LocalDateTime endTime) {
        if (examinationType == null) {
            throw new NullPointerException("Endzeitpunkt der Untersuchung darf nicht null sein.");
        }
        this.endTime = endTime;
    }

    public void updatePatient(Patient patient) {
        if (examinationType == null) {
            throw new NullPointerException("Patient der an Untersuchung teilnimmt darf nicht null sein.");
        }
        this.patient = patient;
    }

    public void updateDoctor(Doctor doctor) {
        if (examinationType == null) {
            throw new NullPointerException("Arzt der Untersuchung durchführt darf nicht null sein.");
        }
        this.doctor = doctor;
    }
}
