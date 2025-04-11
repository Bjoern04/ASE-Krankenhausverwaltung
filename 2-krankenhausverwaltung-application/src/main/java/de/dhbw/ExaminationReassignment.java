package de.dhbw;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.examination.util.ExaminationUpdater;
import de.dhbw.domainservices.ExaminationReassignmentDomainService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ExaminationReassignment {
    private final ExaminationRepository examinationRepository;
    private final DoctorRepository doctorRepository;

    public ExaminationReassignment(ExaminationRepository examinationRepository, DoctorRepository doctorRepository) {
        this.examinationRepository = examinationRepository;
        this.doctorRepository = doctorRepository;
    }

    public String execute(UUID examinationId, UUID doctorId) {
        Examination examination = examinationRepository.findExaminationById(examinationId);
        if (examination == null) {
            throw new IllegalArgumentException("Examination with the ID " + examinationId + " could not not be found");
        }

        Doctor doctor = doctorRepository.findDoctorById(doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with the ID " + doctorId + " could not be found");
        }

        List<Examination> allExaminations = examinationRepository.loadExaminations();
        if (allExaminations == null) {
            throw new IllegalArgumentException("There was an error while loading the examinations. Please check the file with the examinations.");
        }

        boolean examinationReassignment = ExaminationReassignmentDomainService.examinationReassignment(examination, allExaminations, doctor);
        if (examinationReassignment) {
            // Delete the examination from the old doctor
            Doctor oldDoctor = doctorRepository.findDoctorById(examination.getDoctorId());
            if (oldDoctor != null) {
                oldDoctor.removeExamination(examination.getId());
                doctorRepository.updateDoctor(oldDoctor);
            }

            // Update the examination with the new doctor
            examination.updateDoctor(doctorId);
            ExaminationUpdater.updateExaminations(allExaminations, Collections.singletonList(examination));
            examinationRepository.updateExamination(allExaminations);

            return "The examination with the ID " + examinationId + " was successfully reassigned to the doctor with the ID " + doctorId + ".";
        }
        else {
            return "The examination with the ID " + examinationId + " could not be reassigned to the doctor with the ID " + doctorId + ". Please check the dates of the examination and the skillset of the doctor.";
        }
    }
}
