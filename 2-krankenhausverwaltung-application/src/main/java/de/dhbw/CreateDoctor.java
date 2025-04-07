package de.dhbw;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.doctor.repository.DoctorRepository;
import de.dhbw.examination.entity.Examination;
import de.dhbw.examination.repository.ExaminationRepository;
import de.dhbw.shared.Address;
import de.dhbw.shared.Contact;
import de.dhbw.shared.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateDoctor{
    private final DoctorRepository doctorRepository;

    public CreateDoctor(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public UUID execute(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, LocalDate dateOfBirth, String phoneNumber, String email) {

        Doctor doctor = new Doctor.DoctorBuilder(UUID.randomUUID(), new Name(firstName, lastName), new Address(street, houseNumber, zipCode, city), new Contact(phoneNumber, email))
                .withDateOfBirth(dateOfBirth)
                .build();
        doctorRepository.saveDoctor(doctor);
        return doctor.getId();
    }
}
