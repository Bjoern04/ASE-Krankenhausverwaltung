package de.dhbw.use_cases.read;

import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class ReadExamination {
    private final ExaminationRepository examinationRepository;

    public ReadExamination(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
    }

    public List<Examination> execute(boolean all, UUID examinationId) throws FileNotFoundException {
        if (all) {
            List<Examination> allExaminations = examinationRepository.loadExaminations();
            if (allExaminations == null) {
                throw new IllegalArgumentException("There was an error while loading the examinations. Please check the file with the examinations.");
            }
            return allExaminations;
        }
        else {
            Examination examination = examinationRepository.findExaminationById(examinationId);
            if (examination == null) {
                throw new IllegalArgumentException("The doctor with the ID " + examinationId + " could not be found.");
            }
            return List.of(examination);
        }
    }
}
