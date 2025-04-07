package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.shared.Name;

import java.io.File;
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
    public Patient findPatientById(UUID id) {
        try {
            List<Patient> patients = loadPatients();
            return patients.stream().filter(patient -> patient.getId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
    public void deletePatient(UUID patientId) {
        try {
            List<Patient> patientIds = loadPatients();
            Optional<Patient> patientToDelete = patientIds.stream().filter(patient -> patient.getId().equals(patientId)).findFirst();

            if (patientToDelete.isPresent()) {
                patientIds.remove(patientToDelete.get());
                serializer.serializeOverwrite(patientIds, file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(List<Patient> patients) {
        serializer.serializeOverwrite(patients, file.getAbsolutePath());
    }

    @Override
    public List<Patient> findAllPatients() {
        try {
            return loadPatients();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Patient> loadPatients() throws IOException {
        if (file.exists()) {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Patient.class);
            }
        } else {
            return new ArrayList<>();
        }
    }
}
