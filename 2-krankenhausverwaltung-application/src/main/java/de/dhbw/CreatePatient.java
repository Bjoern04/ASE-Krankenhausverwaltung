package de.dhbw;

import de.dhbw.examination.entity.Examination;
import de.dhbw.examination.repository.ExaminationRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreatePatient {
    private final PatientRepository patientRepository;

    public CreatePatient(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public UUID execute(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, LocalDate birthDate, String phoneNumber, String email) {

        // Create name if possible
        Name name = null;
        try {
            name = new Name(lastName, firstName);
        }
        catch (IllegalArgumentException _) {

        }

        // Create address if possible
        Address address = null;
        try {
            address = new Address(street, houseNumber, zipCode, city);
        }
        catch (IllegalArgumentException _) {

        }

        // Create Contact if possible
        Contact contact = null;
        try {
            contact = new Contact(phoneNumber, email);
        }
        catch (IllegalArgumentException _) {
        }


        // Create a new patient.
        Patient patient = new Patient.PatientBuilder(UUID.randomUUID()).withName(name).withAddress(address).withDateOfBirth(birthDate).withContact(contact)
                .build();

        // Save the new patient.
        patientRepository.savePatient(patient);
        return patient.getId();
    }
}
