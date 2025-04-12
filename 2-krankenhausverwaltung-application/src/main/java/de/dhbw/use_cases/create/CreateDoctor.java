package de.dhbw.use_cases.create;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateDoctor{
    private final DoctorRepository doctorRepository;

    public CreateDoctor(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public UUID execute(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, LocalDate dateOfBirth, String phoneNumber, String email, List<ExaminationType> examinationTypes) {

        Doctor doctor = new Doctor.DoctorBuilder(UUID.randomUUID(), new Name(firstName, lastName), new Address(street, houseNumber, zipCode, city), dateOfBirth, new Contact(phoneNumber, email))
                .withExaminationTypes(examinationTypes)
                .build();
        doctorRepository.saveDoctor(doctor);
        return doctor.getId();
    }
}
