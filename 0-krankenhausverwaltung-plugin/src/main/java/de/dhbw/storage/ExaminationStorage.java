package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.commands.exceptions.EmptyFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ExaminationStorage implements ExaminationRepository {
    private final File file;
    private final JsonSerializer serializer;

    public ExaminationStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }


    @Override
    public Examination findExaminationById(UUID id) throws FileNotFoundException {
        List<Examination> examinations = loadExaminations();
        return examinations.stream().filter(examination -> examination.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean saveExamination(Examination examination) {
        serializer.serializeUpdateFile(Collections.singletonList(examination), file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deleteExamination(Examination examination) {
        return false;
    }

    @Override
    public void updateExamination(List<Examination> examinations) {
        serializer.serializeOverwrite(examinations, file.getAbsolutePath());
    }

    @Override
    public List<Examination> findExaminationForPatient(Patient patient) {
        return List.of();
    }

    @Override
    public List<Examination> loadExaminations() throws FileNotFoundException {
        if (file.exists()) {
            if (file.length() == 0) {
                throw new EmptyFile("The file " + file.getAbsolutePath() + " is empty.");
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Examination.class);
            }
        }
        else {
            throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist.");
        }
    }
}
