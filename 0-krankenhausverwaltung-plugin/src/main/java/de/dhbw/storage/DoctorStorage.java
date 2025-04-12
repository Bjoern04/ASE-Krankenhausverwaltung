package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.commands.exceptions.EmptyFile;
import de.dhbw.shared.value_objects.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DoctorStorage implements DoctorRepository {
    private final File file;
    private final JsonSerializer serializer;

    public DoctorStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }

    @Override
    public Doctor findDoctorById(UUID id) throws FileNotFoundException {
        List<Doctor> doctors = loadDoctors();
        return doctors.stream().filter(doctor -> doctor.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Doctor findDoctorByName(Name name) {
        return null;
    }

    @Override
    public boolean saveDoctor(Doctor doctor) {
        serializer.serializeUpdateFile(Collections.singletonList(doctor), file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deleteDoctor(UUID doctorId) throws FileNotFoundException {
        List<Doctor> doctors = loadDoctors();
        Optional<Doctor> doctorToDelete = doctors.stream().filter(doctor -> doctor.getId().equals(doctorId)).findFirst();

        if (doctorToDelete.isPresent()) {
            doctors.remove(doctorToDelete.get());
            serializer.serializeOverwrite(doctors, file.getAbsolutePath());
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        return false;
    }

    @Override
    public List<Doctor> loadDoctors() throws FileNotFoundException {
        if (file.exists()) {
            if (file.length() == 0) {
                throw new EmptyFile("The file " + file.getAbsolutePath() + " is empty.");
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Doctor.class);
            }
        }
        else {
            throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist.");
        }
    }
}
