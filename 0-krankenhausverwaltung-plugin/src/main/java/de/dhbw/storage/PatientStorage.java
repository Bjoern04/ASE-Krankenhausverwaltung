package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.commands.exceptions.EmptyFile;
import de.dhbw.shared.value_objects.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PatientStorage implements PatientRepository {
    private final File file;
    private final JsonSerializer serializer;

    public PatientStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }

    @Override
    public Patient findPatientById(UUID id) throws FileNotFoundException {
        List<Patient> patients = loadPatients();
        return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Patient findPatientByName(Name name) {
        return null;
    }

    @Override
    public boolean savePatient(Patient patient) {
        serializer.serializeUpdateFile(Collections.singletonList(patient), file.getAbsolutePath());
        return true;
    }

    @Override
    public void updatePatient(List<Patient> patients) {
        serializer.serializeOverwrite(patients, file.getAbsolutePath());
    }

    @Override
    public List<Patient> findAllPatients() throws FileNotFoundException {
        return loadPatients();
    }

    @Override
    public List<Patient> loadPatients() throws FileNotFoundException {
        if (file.exists()) {
            if (file.length() == 0) {
                throw new EmptyFile("The file " + file.getAbsolutePath() + " is empty.");
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Patient.class);
            }
        } else {
            throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist.");
        }
    }
}
