package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.shared.Name;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
        serializer.serialize(Collections.singletonList(patient), file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        return false;
    }

    @Override
    public void updatePatient(Patient patient) {

    }

    private List<Patient> loadPatients() throws IOException {
        System.out.println("test1");
        if (file.exists()) {
            System.out.println("test2");
            if (file.length() == 0) {
                System.out.println("test3");
                return new ArrayList<>();
            }
            else {
                System.out.println("test4");
                return serializer.deserialize(file.getAbsolutePath(), Patient.class);
            }
        } else {
            System.out.println("test5");
            return new ArrayList<>();
        }
    }
}
