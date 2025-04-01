package de.dhbw.assignment.entity;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.RoomAddress;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Assignment entity representing an assignment of patients and rooms in the Krankenhausverwaltung.
 */
public class Assignment {
    private final UUID id;

    private Room room;

    private Patient patient;

    private LocalDate dateOfAdmission;

    private LocalDate dateOfDischarge;

    private Assignment (AssignmentBuilder builder) {
        if (builder.id == null) {
            throw new NullPointerException("Belegungs-ID darf nicht null sein.");
        }
        this.id = builder.id;

        if (builder.room == null) {
            throw new NullPointerException("Raum der Belegung darf nicht null sein.");
        }
        this.room = builder.room;

        if (builder.patient == null) {
            throw new NullPointerException("Patient der Belegung darf nicht null sein.");
        }
        this.patient = builder.patient;

        if (builder.dateOfAdmission == null) {
            throw new NullPointerException("Einlieferungsdatum darf nicht null sein.");
        }
        this.dateOfAdmission = builder.dateOfAdmission;

        this.dateOfDischarge = builder.dateOfDischarge;
    }

    public UUID getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void updateRoom(Room room) {
        if (room == null) {
            throw new NullPointerException("Raum der Belegung darf nicht null sein.");
        }
        this.room = room;
    }

    public void updatePatient(Patient patient) {
        if (patient == null) {
            throw new NullPointerException("Patient der Belegung darf nicht null sein.");
        }
        this.patient = patient;
    }

    public void updateDateOfAdmission(LocalDate dateOfAdmission) {
        if (dateOfAdmission == null) {
            throw new NullPointerException("Einlieferungsdatum darf nicht null sein.");
        }
        this.dateOfAdmission = dateOfAdmission;
    }

    public void updateDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    /**
     * Builder class for Assignment following the Builder design pattern.
     */
    public static class AssignmentBuilder {
        private final UUID id;

        private Room room;

        private Patient patient;

        private LocalDate dateOfAdmission;

        private LocalDate dateOfDischarge;

        public AssignmentBuilder (UUID id, Room room, Patient patient, LocalDate dateOfAdmission) {
            this.id = id;
            this.room = room;
            this.patient = patient;
            this.dateOfAdmission = dateOfAdmission;
        }

        public void withDateOfDischarge (LocalDate dateOfDischarge) {
            this.dateOfDischarge = dateOfDischarge;
        }

        /**
         * Building a {@link Assignment}.
         *
         * @return Returns the created {@link Assignment} object.
         */
        public Assignment build() {
            return new Assignment(this);
        }
    }
}
