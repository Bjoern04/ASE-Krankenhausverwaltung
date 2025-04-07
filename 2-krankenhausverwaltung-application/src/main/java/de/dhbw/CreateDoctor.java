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
    private final ExaminationRepository examinationRepository;

    public CreateDoctor(DoctorRepository doctorRepository, ExaminationRepository examinationRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
    }

    public UUID execute(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, LocalDate birthDate, String phoneNumber, String email, List<UUID> examinationIdsOfDoctor) {
        // Check examinations
        if (examinationIdsOfDoctor != null && !examinationIdsOfDoctor.isEmpty()) {
            List<UUID> allExaminationIds = examinationRepository.loadAllExaminations().stream().map(Examination::getId).toList();
            for (UUID examinationId : examinationIdsOfDoctor) {
                if (!allExaminationIds.contains(examinationId)) {
                    throw new IllegalArgumentException("An examination with the ID " + examinationId + " does not exist.");
                }
            }
        }

        Doctor doctor = new Doctor.DoctorBuilder(UUID.randomUUID(), new Name(firstName, lastName), new Address(street, houseNumber, zipCode, city), birthDate, new Contact(phoneNumber, email))
                .withExaminations(examinationIdsOfDoctor)
                .build();
        doctorRepository.saveDoctor(doctor);
        return doctor.getId();
    }
}
