package de.dhbw.doctor.repository;

import de.dhbw.doctor.entity.Doctor;
import de.dhbw.shared.Name;

import java.util.UUID;

public interface DoctorRepository {
    Doctor findDoctorById(UUID id);

    Doctor findDoctorByName (Name name);

    boolean saveDoctor(Doctor doctor);

    boolean deleteDoctor(Doctor doctor);

    boolean updateDoctor (Doctor doctor);
}
