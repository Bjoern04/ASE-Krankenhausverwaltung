package de.dhbw.use_cases.create;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateExamination{
    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public CreateExamination(ExaminationRepository examinationRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.examinationRepository = examinationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public UUID execute(ExaminationType examinationType, LocalDateTime startDate, LocalDateTime endDate, UUID patientId, UUID doctorId) throws FileNotFoundException {
        // Check if patient exists
        Patient patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " does not exist.");
        }

        // Check if doctor exists
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " does not exist.");
        }

        Examination examination = new Examination(UUID.randomUUID(), examinationType, startDate, endDate, patientId, doctorId);
        examinationRepository.saveExamination(examination);
        return examination.getId();

    }
}
