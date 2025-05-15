package de.dhbw.aggregates.examination.util;

import de.dhbw.aggregates.examination.entity.Examination;

import java.util.Comparator;
import java.util.List;

public class ExaminationWithPatientName {

    private String patientName;
    private Examination examination;

    public ExaminationWithPatientName(String patientName, Examination examination) {
        this.patientName = patientName;
        this.examination = examination;
    }

    public Examination getExamination() {
        return examination;
    }

    public String getPatientName() {
        return patientName;
    }

    public static List<ExaminationWithPatientName> sortByDate(List<ExaminationWithPatientName> examinationsOfDoctor) {
        examinationsOfDoctor.sort(Comparator.comparing(e -> e.getExamination().getStartTime()));
        return examinationsOfDoctor;
    }

    @Override
    public String toString() {
        return "Examination for: " + patientName + " - " + examination.toString();
    }
}
