package de.dhbw;

import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;

import java.util.List;
import java.util.UUID;

public class ReadPatient {
    private final PatientRepository patientRepository;

    public ReadPatient(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> execute(boolean all, UUID patientId) {
        if (all) {
            List<Patient> allPatients = patientRepository.loadPatients();
            if (allPatients == null) {
                throw new IllegalArgumentException("There was an error while loading the patients. Please check the file with the patients.");
            }
            return allPatients;
        }
        else {
            Patient patient = patientRepository.findPatientById(patientId);
            if (patient == null) {
                throw new IllegalArgumentException("The patient with the ID " + patientId + " could not found.");
            }
            return List.of(patient);
        }
    }
}
