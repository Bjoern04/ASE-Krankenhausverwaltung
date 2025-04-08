package de.dhbw.domainservices;

import de.dhbw.aggregates.examination.entity.Examination;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorDeleteDomainService {
    /**
     * This method checks if the doctor with the given ID has any examinations that are still in the future.
     * If so, it returns null because the doctor can´t be deleted. Otherwise, it returns a list of examinations in which the doctor took part.
     * The doctor is only allowed to be deleted if all examinations are in the past.
     *
     * @param doctorId The ID of the doctor to check.
     * @param alLExaminations The list of all examinations to check against.
     * @return A list of past examinations for the doctor, or null if there are still future examinations for the doctor.
     */
    public static ArrayList<Examination> deleteDoctor(UUID doctorId, List<Examination> alLExaminations) {
        ArrayList<Examination> examinations = new ArrayList<>();
        for (Examination examination : alLExaminations) {
            if (examination.getDoctorId().equals(doctorId)) {
                LocalDateTime endTime = examination.getEndTime();
                if (endTime.isAfter(LocalDateTime.now())) {
                    return null;
                }
                else {
                    examinations.add(examination);
                }
            }
        }
        return examinations;
    }
}
