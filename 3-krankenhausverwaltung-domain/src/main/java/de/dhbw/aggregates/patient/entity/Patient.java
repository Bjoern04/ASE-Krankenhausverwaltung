package de.dhbw.aggregates.patient.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Patient entity representing a patient in the Krankenhausverwaltung.
 */
public class Patient {
    // Unique identifier for the patient
    private final UUID id;

    // Patient's full name
    private Name name;

    // Patient's address
    private Address address;

    // Patient's date of birth
    private LocalDate dateOfBirth;

    // List of medical examinations
    private List<UUID> examinationIds;

    // Patient contact information
    private Contact contact;

    // Assignment information (if applicable)
    private UUID assignmentId;

    // Private constructor to enforce builder usage
    private Patient(PatientBuilder builder) {
        if (builder.id == null) {
            throw new NullPointerException("Patient-ID darf nicht null sein.");
        }
        this.id = builder.id;

        // Optional fields
        this.name = builder.name;
        this.address = builder.address;
        this.dateOfBirth = builder.dateOfBirth;
        this.examinationIds = builder.examinationIds != null
                ? new ArrayList<>(builder.examinationIds)
                : new ArrayList<>();
        this.contact = builder.contact;
        this.assignmentId = builder.assignmentId;
    }

    public Patient removeExamination(UUID examinationId) {
        if (this.examinationIds != null) {
            this.examinationIds.remove(examinationId);
        }
        return this;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<UUID> getExaminationIds() {
        return examinationIds;
    }

    public Contact getContact() {
        return contact;
    }

    public UUID getAssignmentId() {
        return assignmentId;
    }

    // Update methods
    public void updateName(Name updatedName) {
        this.name = updatedName;
    }

    public void updateAddress (Address updatedAddress) {
        this.address = updatedAddress;
    }

    public void updateDateOfBirth (LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addExamination (UUID examinationId) {
        if (examinationId == null) {
            throw new NullPointerException("Die Untersuchung darf nicht null sein.");
        }
        if (!this.examinationIds.contains(examinationId)) {
            this.examinationIds.add(examinationId);
        }
    }

    public void addExaminations (List<UUID> examinationIds) {
        for (UUID examinationId : examinationIds) {
            if (!this.examinationIds.contains(examinationId)) {
                this.examinationIds.add(examinationId);
            }
        }
    }

    public void updateContact(Contact updatedContact) {
        this.contact = updatedContact;
    }

    public void updateAssignment(UUID updatedAssignmentId) {
        if (updatedAssignmentId != null && !updatedAssignmentId.equals(this.assignmentId)) {
            this.assignmentId = updatedAssignmentId;
        }
    }

    @Override
    public String toString() {
        StringBuilder examinationIdsString = new StringBuilder();

        if (examinationIds != null) {
            for (Object id : examinationIds) {
                if (id != null) {
                    examinationIdsString.append(id).append(", ");
                } else {
                    examinationIdsString.append("null, ");
                }
            }
            if (examinationIdsString.length() > 2) {
                examinationIdsString.setLength(examinationIdsString.length() - 2);
            }
        } else {
            examinationIdsString.append("null");
        }

        return "ID: " + id +
                ", Name: " + (name != null ? name.toString() : "null") +
                ", Address: " + (address != null ? address.toString() : "null") +
                ", Date of Birth: " + (dateOfBirth != null ? dateOfBirth.toString() : "null") +
                ", Examination IDs: [" + examinationIdsString.toString() + "]" +
                ", Contact: " + (contact != null ? contact.toString() : "null") +
                ", Assignment ID: " + assignmentId;
    }

    /**
     * Builder class for Patient following the Builder design pattern.
     */
    public static class PatientBuilder {
        private final UUID id;
        private Name name;
        private Address address;
        private LocalDate dateOfBirth;
        private List<UUID> examinationIds;
        private Contact contact;
        private UUID assignmentId;

        @JsonCreator
        public PatientBuilder(UUID id) {
            this.id = id;
        }

        public PatientBuilder withName (Name name) {
            this.name = name;
            return this;
        }

        public PatientBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public PatientBuilder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PatientBuilder withExamination(List<UUID> examinationIds) {
            this.examinationIds = examinationIds;
            return this;
        }

        public PatientBuilder withContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public PatientBuilder withAssignment(UUID assignmentId) {
            this.assignmentId = assignmentId;
            return this;
        }

        /**
         * Building a {@link Patient}.
         *
         * @return Returns the created {@link Patient} object.
         */
        public Patient build() {
            return new Patient(this);
        }
    }
}