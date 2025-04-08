package de.dhbw;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;

import java.time.LocalDate;
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
