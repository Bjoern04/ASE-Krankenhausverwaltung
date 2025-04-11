package de.dhbw.aggregates.doctor.entity;

import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;

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

    private Contact contact;

    private List<UUID> examinationIds;

    private ArrayList<ExaminationType> examinationTypes;

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

        if (builder.contact == null) {
            throw new NullPointerException("Der Kontakt des Arztes darf nicht null sein.");
        }
        this.contact = builder.contact;

        // Optional fields
        this.dateOfBirth = builder.dateOfBirth;
        this.examinationIds = builder.examinationIds != null
                ? new ArrayList<>(builder.examinationIds)
                : new ArrayList<>();

        this.examinationTypes = builder.examinationTypes != null
                ? new ArrayList<>(builder.examinationTypes)
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


    public Contact getContact() {
        return contact;
    }

    public List<UUID> getExaminationIds() {
        return examinationIds;
    }

    public List<ExaminationType> getExaminationTypes() {
        return examinationTypes;
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

    public void updateExaminations(List<UUID> examinations) {
        for (UUID examinationId : examinationIds) {
            if (!this.examinationIds.contains(examinationId)) {
                this.examinationIds.add(examinationId);
            }
        }
    }

    public void updateContact(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Der Kontakt des Arztes darf nicht null sein.");
        }
        this.contact = contact;
    }

    public void addExamination(UUID examination) {
        if (examination == null) {
            throw new NullPointerException("Die Untersuchung darf nicht null sein.");
        }
        if (!this.examinationIds.contains(examination)) {
            this.examinationIds.add(examination);
        }
    }

    public void addExaminationType(ExaminationType examinationType) {
        if (examinationType == null) {
            throw new NullPointerException("The examination type must not be null.");
        }
        if (!this.examinationTypes.contains(examinationType)) {
            this.examinationTypes.add(examinationType);
        }
    }

    public void removeExamination(UUID examinationId) {
        if (examinationId == null) {
            throw new NullPointerException("The examination must not be null.");
        }
        this.examinationIds.remove(examinationId);
    }


    /**
     * Builder class for Doctor following the Builder design pattern.
     */
    public static class DoctorBuilder {
        private final UUID id;

        private Name name;

        private Address address;

        private LocalDate dateOfBirth;

       private Contact contact;

        private List<UUID> examinationIds;

        private List<ExaminationType> examinationTypes;

        public DoctorBuilder(UUID id, Name name, Address address, LocalDate dateOfBirth, Contact contact) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.dateOfBirth = dateOfBirth;
            this.contact = contact;
        }

        public DoctorBuilder withExaminationTypes(List<ExaminationType> examinationTypes) {
            this.examinationTypes = examinationTypes;
            return this;
        }

        public DoctorBuilder withExaminations(List<UUID> examinations) {
            this.examinationIds = examinations;
            return this;
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
