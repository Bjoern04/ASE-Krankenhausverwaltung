package de.dhbw.patient.entity;

import de.dhbw.examination.entity.Examination;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Date;
import de.dhbw.shared.Name;

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
    private List<Examination> examinations;

    // Patient contact information
    private Contact contact;

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
        this.examinations = builder.examinations != null
                ? new ArrayList<>(builder.examinations)
                : new ArrayList<>();
        this.contact = builder.contact;
    }

    public Patient removeExamination(Examination examination) {
        if (this.examinations != null) {
            this.examinations.remove(examination);
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

    public List<Examination> getExaminations() {
        return new ArrayList<>(examinations);
    }

    public Contact getContact() {
        return contact;
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

    public void addExamination (Examination examination) {
        this.examinations.add(examination);
    }

    public void addExaminations (List<Examination> examinations) {
        this.examinations.addAll(examinations);
    }

    public void updateContact(Contact updatedContact) {
        this.contact = updatedContact;
    }

    /**
     * Builder class for Patient following the Builder design pattern.
     */
    public static class PatientBuilder {
        private final UUID id;
        private Name name;
        private Address address;
        private LocalDate dateOfBirth;
        private List<Examination> examinations;
        private Contact contact;

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

        public PatientBuilder withExamination(List<Examination> examinations) {
            this.examinations = new ArrayList<>(examinations);
            return this;
        }

        public PatientBuilder withContact(Contact contact) {
            this.contact = contact;
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