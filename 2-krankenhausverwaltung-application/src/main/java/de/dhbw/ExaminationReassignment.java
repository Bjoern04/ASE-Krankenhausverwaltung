package de.dhbw;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;

import java.util.UUID;

public class ExaminationReassignment {
    private final ExaminationRepository examinationRepository;
    private final DoctorRepository doctorRepository;

    public ExaminationReassignment(ExaminationRepository examinationRepository, DoctorRepository doctorRepository) {
        this.examinationRepository = examinationRepository;
        this.doctorRepository = doctorRepository;
    }

    public void execute(UUID examinationId, UUID doctorId) {
        Examination examination = examinationRepository.findExaminationById(examinationId);
        if (examination == null) {
            System.out.println("Examination with ID " + examinationId + " not found.");
            return;
        }
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor with ID " + doctorId + " not found.");
            return;
        }

    }
}
