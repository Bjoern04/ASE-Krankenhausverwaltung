package de.dhbw.aggregates.assignment.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            throw new NullPointerException("The ID of the assignment must not be null.");
        }
        this.id = builder.id;

        if (builder.roomId == null) {
            throw new NullPointerException("Room of the assignment must not be null.");
        }
        this.roomId = builder.roomId;

        if (builder.patientId == null) {
            throw new NullPointerException("Patient of the assignment must not be null.");
        }
        this.patientId = builder.patientId;

        if (builder.dateOfAdmission == null) {
            throw new NullPointerException("Date of admission must not be null.");
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
            throw new NullPointerException("The room of the assignment must not be null.");
        }
        this.roomId = roomId;
    }

    public void updatePatientID(UUID patientId) {
        if (patientId == null) {
            throw new NullPointerException("The patient of the assignment must not be null.");
        }
        this.patientId = patientId;
    }

    public void updateDateOfAdmission(LocalDate dateOfAdmission) {
        if (dateOfAdmission == null) {
            throw new NullPointerException("The date of admission must not be null.");
        }
        this.dateOfAdmission = dateOfAdmission;
    }

    public void updateDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }

    @Override
    public String toString() {
        return "Admission{" +
                "Id: " + id +
                ", RoomId: " + roomId +
                ", PatientId: " + patientId +
                ", DateOfAdmission: " + dateOfAdmission +
                ", DateOfDischarge: " + dateOfDischarge +
                '}';
    }

    /**
     * Builder class for Assignment following the Builder design pattern.
     */
    public static class AssignmentBuilder {
        private final UUID id;

        private final UUID roomId;

        private final UUID patientId;

        private final LocalDate dateOfAdmission;

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
