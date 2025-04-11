package de.dhbw.aggregates.doctor.repository;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.shared.value_objects.Name;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository {
    Doctor findDoctorById(UUID id);

    Doctor findDoctorByName (Name name);

    boolean saveDoctor(Doctor doctor);

    boolean deleteDoctor(UUID doctorId);

    boolean updateDoctor (Doctor doctor);

    List<Doctor> loadDoctors();
}
