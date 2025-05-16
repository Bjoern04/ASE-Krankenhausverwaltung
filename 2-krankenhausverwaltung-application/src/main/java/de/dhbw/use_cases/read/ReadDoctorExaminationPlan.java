package de.dhbw.use_cases.read;

import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.examination.util.ExaminationWithPatientName;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.domainservices.DoctorExaminationPlanService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class ReadDoctorExaminationPlan {

    DoctorRepository doctorRepository;
    ExaminationRepository examinationRepository;
    PatientRepository patientRepository;

    public ReadDoctorExaminationPlan(DoctorRepository doctorRepository, ExaminationRepository examinationRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
        this.patientRepository = patientRepository;
    }

    public List<ExaminationWithPatientName> execute(UUID doctorId, boolean sortByDate) throws FileNotFoundException {
        if (doctorId == null) {
            throw new IllegalArgumentException("Doctor ID cannot be null.");
        }
        DoctorExaminationPlanService doctorExaminationPlanService = new DoctorExaminationPlanService(doctorRepository, examinationRepository, patientRepository);
        List<ExaminationWithPatientName> examinationsOfDoctor = doctorExaminationPlanService.createDoctorExaminationPlan(doctorId);

        if (sortByDate) {
            ExaminationWithPatientName.sortByDate(examinationsOfDoctor);
        }
        return examinationsOfDoctor;
    }
}
