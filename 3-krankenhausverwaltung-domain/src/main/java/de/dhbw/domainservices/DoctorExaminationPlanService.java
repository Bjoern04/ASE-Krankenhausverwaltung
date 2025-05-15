package de.dhbw.domainservices;


import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.examination.util.ExaminationWithPatientName;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.shared.value_objects.Name;

import java.io.FileNotFoundException;
import java.util.*;

public class DoctorExaminationPlanService {
    private final DoctorRepository doctorRepository;
    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;

    public DoctorExaminationPlanService(DoctorRepository doctorRepository, ExaminationRepository examinationRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
        this.patientRepository = patientRepository;
    }

    public List<ExaminationWithPatientName> createDoctorExaminationPlan(UUID doctorId) throws FileNotFoundException {
        if (doctorRepository.findDoctorById(doctorId) == null) {
            throw new IllegalArgumentException("Please enter a valid doctor ID.");
        }

        List<Examination> examinations = examinationRepository.loadExaminations();
        List<ExaminationWithPatientName> examinationsOfDoctor = new ArrayList<>();

        for (Examination examination : examinations) {
            if (examination.getDoctorId().equals(doctorId)) {
                Patient patient = patientRepository.findPatientById(examination.getPatientId());

                if (patient == null) {
                    throw new IllegalArgumentException("Patient " + examination.getPatientId() + " not found.");
                }

                Name name = patient.getName();
                if (name == null) {
                    examinationsOfDoctor.add(new ExaminationWithPatientName("Unnamed Patient", examination));
                }
                else {
                    examinationsOfDoctor.add(new ExaminationWithPatientName(name.toString(), examination));
                }
            }
        }

        return examinationsOfDoctor;
    }
}
