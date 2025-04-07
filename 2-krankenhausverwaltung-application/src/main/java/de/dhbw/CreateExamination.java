package de.dhbw;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.doctor.repository.DoctorRepository;
import de.dhbw.examination.entity.Examination;
import de.dhbw.examination.repository.ExaminationRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.shared.ExaminationType;

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

    public UUID execute(ExaminationType examinationType, LocalDateTime startDate, LocalDateTime endDate, UUID patientId, UUID doctorId) {
        // check if patient exists
        Patient patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " does not exist.");
        }

        // check if doctor exists
        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " does not exist.");
        }

        Examination examination = new Examination(UUID.randomUUID(), examinationType, startDate, endDate, patientId, doctorId);
        examinationRepository.saveExamination(examination);
        return examination.getId();

    }
}
