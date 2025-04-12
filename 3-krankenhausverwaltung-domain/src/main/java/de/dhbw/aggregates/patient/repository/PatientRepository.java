package de.dhbw.aggregates.patient.repository;

import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.shared.value_objects.Name;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface PatientRepository {
    Patient findPatientById(UUID id) throws FileNotFoundException;

    List<Patient> findAllPatients() throws FileNotFoundException;

    Patient findPatientByName(Name name);

    boolean savePatient(Patient patient);

    void deletePatient(UUID patientId) throws FileNotFoundException;

    void updatePatient (List<Patient> patients);

    List<Patient> loadPatients () throws FileNotFoundException;
}
