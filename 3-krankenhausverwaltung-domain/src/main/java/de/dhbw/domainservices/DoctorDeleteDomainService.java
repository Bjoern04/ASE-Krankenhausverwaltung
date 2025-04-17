package de.dhbw.domainservices;

import de.dhbw.aggregates.examination.entity.Examination;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorDeleteDomainService {

    public static ArrayList<Examination> deleteDoctor(UUID doctorId, List<Examination> alLExaminations) {
        ArrayList<Examination> examinations = new ArrayList<>();
        for (Examination examination : alLExaminations) {
            if (examination.getDoctorId().equals(doctorId)) {
                LocalDateTime endTime = examination.getEndTime();
                if (endTime.isAfter(LocalDateTime.now())) {
                    return null;
                }
                else {
                    examination.updateDoctor(null);
                    examinations.add(examination);
                }
            }
        }
        return examinations;
    }
}
