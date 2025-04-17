package de.dhbw.aggregates.examination.util;

import de.dhbw.aggregates.examination.entity.Examination;

import java.util.List;

public class ExaminationUpdater {
    public static List<Examination> updateExaminations(List<Examination> allExaminations, List<Examination> examinationsToUpdate) {
        for (Examination examinationToUpdate : examinationsToUpdate) {
            for (Examination examination : allExaminations) {
                if (examinationToUpdate.getId().equals(examination.getId())) {
                    examination.updateExaminationType(examinationToUpdate.getExaminationType());
                    examination.updateStartTime(examinationToUpdate.getStartTime());
                    examination.updateEndTime(examinationToUpdate.getEndTime());
                    examination.updatePatient(examinationToUpdate.getPatientId());
                    examination.updateDoctor(examinationToUpdate.getDoctorId());
                    break;
                }
            }
        }
        return allExaminations;
    }
}
