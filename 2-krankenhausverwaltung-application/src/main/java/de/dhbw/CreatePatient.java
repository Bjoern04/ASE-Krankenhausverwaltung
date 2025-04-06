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
    private final ExaminationRepository examinationRepository;

    public CreatePatient(PatientRepository patientRepository, ExaminationRepository examinationRepository) {
        this.patientRepository = patientRepository;
        this.examinationRepository = examinationRepository;
    }

    public UUID execute(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, LocalDate birthDate, List<UUID> examinationIdsOfPatient, String phoneNumber, String email, UUID assigmentId) {

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

        // Check examinations
        if (examinationIdsOfPatient != null && !examinationIdsOfPatient.isEmpty()) {
            List<UUID> allExaminationIds = examinationRepository.loadAllExaminations().stream().map(Examination::getId).toList();
            for (UUID examinationId : examinationIdsOfPatient) {
                if (!allExaminationIds.contains(examinationId)) {
                    throw new IllegalArgumentException("Eine Untersuchung mit der ID " + examinationId + " existiert nicht.");
                }
            }
        }

        // Create a new patient.
        Patient patient = new Patient.PatientBuilder(UUID.randomUUID()).withName(name).withAddress(address).withDateOfBirth(birthDate).withExamination(examinationIdsOfPatient).withContact(contact).withAssignment(assigmentId)
                .build();

        // Save the new patient.
        patientRepository.savePatient(patient);
        return patient.getId();
    }
}
