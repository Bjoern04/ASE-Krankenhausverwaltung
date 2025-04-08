package de.dhbw.aggregates.patient.repository;

import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.shared.value_objects.Name;

import java.util.List;
import java.util.UUID;

public interface PatientRepository {
    Patient findPatientById(UUID id);

    List<Patient> findAllPatients();

    Patient findPatientByName(Name name);

    boolean savePatient(Patient patient);

    void deletePatient(UUID patientId);

    void updatePatient (List<Patient> patients);
}
