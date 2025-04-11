package de.dhbw;

import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.examination.util.ExaminationUpdater;
import de.dhbw.domainservices.DoctorDeleteDomainService;

import java.util.List;
import java.util.UUID;

public class DeleteDoctor {
    private final DoctorRepository doctorRepository;
    private final ExaminationRepository examinationRepository;

    public DeleteDoctor(DoctorRepository doctorRepository, ExaminationRepository examinationRepository) {
        this.doctorRepository = doctorRepository;
        this.examinationRepository = examinationRepository;
    }

    public String execute(UUID doctorId) {
        List<Examination> allExaminations = examinationRepository.loadExaminations();
        List<Examination> examinationsToDeleteDoctorFrom = DoctorDeleteDomainService.deleteDoctor(doctorId, allExaminations);

        if (examinationsToDeleteDoctorFrom == null) {
            return "Doctor with the ID " + doctorId + " has still examinations in the future and therefore cannot be deleted.";
        }

        ExaminationUpdater.updateExaminations(allExaminations, examinationsToDeleteDoctorFrom);
        examinationRepository.updateExamination(allExaminations);

        boolean doctorFound = doctorRepository.deleteDoctor(doctorId);
        if (doctorFound) {
            return "Doctor with the ID " + doctorId + " was successfully deleted.";
        }
        else {
            return "Doctor with the ID " + doctorId + " was not found and therefore could not be deleted.";
        }
    }
}
