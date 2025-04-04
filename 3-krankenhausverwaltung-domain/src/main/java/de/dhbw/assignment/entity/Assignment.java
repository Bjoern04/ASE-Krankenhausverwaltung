package de.dhbw.assignment.entity;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import de.dhbw.doctor.entity.Doctor;
import de.dhbw.patient.entity.Patient;
import de.dhbw.room.entity.Room;
import de.dhbw.shared.RoomAddress;

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

    public UUID getRoom() {
        return roomId;
    }

    public UUID getPatient() {
        return patientId;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }

    public void updateRoom(UUID room) {
        if (room == null) {
            throw new NullPointerException("Raum der Belegung darf nicht null sein.");
        }
        this.roomId = room;
    }

    public void updatePatient(UUID patient) {
        if (patient == null) {
            throw new NullPointerException("Patient der Belegung darf nicht null sein.");
        }
        this.patientId = patient;
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

        public AssignmentBuilder (UUID id, UUID room, UUID patient, LocalDate dateOfAdmission) {
            this.id = id;
            this.roomId = room;
            this.patientId = patient;
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
