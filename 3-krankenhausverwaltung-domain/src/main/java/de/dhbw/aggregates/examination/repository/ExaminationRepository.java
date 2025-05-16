package de.dhbw.aggregates.examination.repository;

import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.patient.entity.Patient;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface ExaminationRepository {
    Examination findExaminationById(UUID id) throws FileNotFoundException;

    boolean saveExamination (Examination examination);


    List<Examination> loadExaminations() throws FileNotFoundException;
}
