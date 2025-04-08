package de.dhbw.aggregates.assignment.entity;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Assignment entity representing an assignment of patients and rooms in the Krankenhausverwaltung.
 */
public class Assignment {
    private final UUID id;

    private UUID roomId;

    private UUID patientId;

    private LocalDate dateOfAdmission;

    private LocalDate dateOfDischarge;

    private Assignment (AssignmentBuilder builder) {
        if (builder.id == null) {
            throw new NullPointerException("Belegungs-ID darf nicht null sein.");
        }
        this.id = builder.id;

        if (builder.roomId == null) {
            throw new NullPointerException("Raum der Belegung darf nicht null sein.");
        }
        this.roomId = builder.roomId;

        if (builder.patientId == null) {
            throw new NullPointerException("Patient der Belegung darf nicht null sein.");
        }
        this.patientId = builder.patientId;

        if (builder.dateOfAdmission == null) {
            throw new NullPointerException("Einlieferungsdatum darf nicht null sein.");
        }
        this.dateOfAdmission = builder.dateOfAdmission;

        this.dateOfDischarge = builder.dateOfDischarge;
    }

    public UUID getId() {
        return id;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void updateRoomId(UUID roomId) {
        if (roomId == null) {
            throw new NullPointerException("Raum der Belegung darf nicht null sein.");
        }
        this.roomId = roomId;
    }

    public void updatePatientID(UUID patientId) {
        if (patientId == null) {
            throw new NullPointerException("Patient der Belegung darf nicht null sein.");
        }
        this.patientId = patientId;
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

        private UUID roomId;

        private UUID patientId;

        private LocalDate dateOfAdmission;

        private LocalDate dateOfDischarge;

        public AssignmentBuilder (UUID id, UUID roomId, UUID patientId, LocalDate dateOfAdmission) {
            this.id = id;
            this.roomId = roomId;
            this.patientId = patientId;
            this.dateOfAdmission = dateOfAdmission;
        }

        public AssignmentBuilder withDateOfDischarge (LocalDate dateOfDischarge) {
            this.dateOfDischarge = dateOfDischarge;
            return this;
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
