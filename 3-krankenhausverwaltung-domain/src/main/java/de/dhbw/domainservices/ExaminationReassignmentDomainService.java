package de.dhbw.domainservices;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;

import java.time.LocalDateTime;
import java.util.List;

public class ExaminationReassignmentDomainService {
    public static boolean examinationReassignment(Examination examination, List<Examination> allExaminations, Doctor doctor) {
        // Check that the examination is not in the past
        if (examination.getStartTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Check that the examination is not already assigned to the doctor
        if (examination.getDoctorId().equals(doctor.getId())) {
            return false;
        }

        // Check that the doctor is not already assigned to another examination at the same time
        for (Examination ex : allExaminations) {
            if (ex.getDoctorId().equals(doctor.getId()) && ex.getStartTime().isBefore(examination.getEndTime()) && examination.getStartTime().isBefore(ex.getEndTime())) {
                return false;
            }
        }

        // Check that the doctor is able to preform this kind of examination
        List <ExaminationType> examinationTypes = doctor.getExaminationTypes();
        for (ExaminationType examinationType : examinationTypes) {
            if (examination.getExaminationType().equals(examinationType)) {
                return true;
            }
        }
        return false;
    }
}
