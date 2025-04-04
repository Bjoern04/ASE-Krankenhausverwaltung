package de.dhbw.examination.repository;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.examination.entity.Examination;
import de.dhbw.patient.entity.Patient;

import javax.print.Doc;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ExaminationRepository {
    Examination findExaminationById(UUID id);

    boolean saveExamination (Examination examination);

    boolean deleteExamination (Examination examination);

    boolean updateExamination (Examination examination);

    List<Examination> findExaminationForPatient (Patient patient);

    List<Examination> findExaminationForDoctor (Doctor doctor);
}
