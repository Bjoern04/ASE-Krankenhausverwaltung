package de.dhbw.doctor.entity;

import de.dhbw.examination.entity.Examination;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Name;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Doctor entity representing a doctor in the Krankenhausverwaltung.
 */
public class Doctor {
    private final UUID id;

    private Name name;

    private Address address;

    private LocalDate dateOfBirth;

    private List<Examination> examinations;

    private Contact contact;

    private Doctor (DoctorBuilder builder) {
        if (builder.id == null) {
            throw new NullPointerException("Arzt-ID darf nicht null sein.");
        }
        this.id = builder.id;

        if (builder.name == null) {
            throw new NullPointerException("Der Name des Arztes darf nicht null sein.");
        }
        this.name = builder.name;

        if (builder.address == null) {
            throw new NullPointerException("Die Adresse des Arztes darf nicht null sein.");
        }
        this.address = builder.address;

        if (builder.dateOfBirth == null) {
            throw new NullPointerException("Der Geburtstag des Arztes darf nicht null sein.");
        }
        this.dateOfBirth = builder.dateOfBirth;

        if (builder.contact == null) {
            throw new NullPointerException("Der Kontakt des Arztes darf nicht null sein.");
        }
        this.contact = builder.contact;

        // Optional fields
        this.examinations = builder.examinations != null
                ? new ArrayList<>(builder.examinations)
                : new ArrayList<>();
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
        return examinations;
    }

    public Contact getContact() {
        return contact;
    }



    public void updateName(Name name) {
        if (name == null) {
            throw new NullPointerException("Der Name des Arztes darf nicht null sein.");
        }
        this.name = name;
    }

    public void updateAddress(Address address) {
        if (address == null) {
            throw new NullPointerException("Die Adresse des Arztes darf nicht null sein.");
        }
        this.address = address;
    }

    public void updateDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new NullPointerException("Der Geburtstag des Arztes darf nicht null sein.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public void updateExaminations(List<Examination> examinations) {
        this.examinations = examinations != null
                ? new ArrayList<>(examinations)
                : new ArrayList<>();
    }

    public void updateContact(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Der Kontakt des Arztes darf nicht null sein.");
        }
        this.contact = contact;
    }

    public void addExamination(Examination examination) {
        this.examinations.add(examination);
    }

    public void addExaminations(List<Examination> examinations) {
        if (examinations != null) {
            this.examinations.addAll(examinations);
        }
    }

    /**
     * Builder class for Doctor following the Builder design pattern.
     */
    public static class DoctorBuilder {
        private final UUID id;

        private Name name;

        private Address address;

        private LocalDate dateOfBirth;

        private List<Examination> examinations;

        private Contact contact;

        public DoctorBuilder(UUID id, Name name, Address address, LocalDate dateOfBirth, Contact contact) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.contact = contact;
        }

        public void withExaminations(List<Examination> examinations) {
            this.examinations = examinations;
        }

        /**
         * Building a {@link Doctor}.
         *
         * @return Returns the created {@link Doctor} object.
         */
        public Doctor build() {
            return new Doctor(this);
        }
    }

}
