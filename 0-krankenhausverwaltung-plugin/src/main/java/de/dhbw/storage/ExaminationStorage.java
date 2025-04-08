package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.patient.entity.Patient;

import java.io.File;
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
    public Examination findExaminationById(UUID id) {
        return null;
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
    public boolean updateExamination(Examination examination) {
        return false;
    }

    @Override
    public List<Examination> findExaminationForPatient(Patient patient) {
        return List.of();
    }

    @Override
    public List<Examination> findExaminationForDoctor(Doctor doctor) {
        return List.of();
    }

    @Override
    public List<Examination> loadAllExaminations() {
        if (file.exists()) {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Examination.class);
            }
        } else {
            return new ArrayList<>();
        }
    }
}
