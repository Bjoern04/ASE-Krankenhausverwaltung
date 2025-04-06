package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.doctor.entity.Doctor;
import de.dhbw.doctor.repository.DoctorRepository;
import de.dhbw.shared.Name;

import java.io.File;
import java.util.Collections;
import java.util.UUID;

public class DoctorStorage implements DoctorRepository {
    private final File file;
    private final JsonSerializer serializer;

    public DoctorStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }

    @Override
    public Doctor findDoctorById(UUID id) {
        return null;
    }

    @Override
    public Doctor findDoctorByName(Name name) {
        return null;
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        serializer.serialize(Collections.singletonList(doctor), file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deleteDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }
}
