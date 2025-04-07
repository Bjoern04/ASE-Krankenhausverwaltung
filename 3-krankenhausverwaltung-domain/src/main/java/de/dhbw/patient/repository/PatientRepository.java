package de.dhbw.patient.repository;

import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.Name;

import java.util.List;
import java.util.UUID;

public interface PatientRepository {
    Patient findPatientById(UUID id);

    Patient findPatientByName(Name name);

    boolean savePatient(Patient patient);

    void deletePatient(UUID patientId);

    void updatePatient (Patient patient);
}
