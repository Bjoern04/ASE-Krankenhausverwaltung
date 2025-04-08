package de.dhbw.aggregates.patient.util;

import de.dhbw.aggregates.patient.entity.Patient;

import java.util.List;

public class PatientUpdater {
    /**
     * Updates the patient list with the updated patient information.
     *
     * @param patientToUpdate The patient object containing updated information.
     * @param allPatients The list of all patients.
     * @return The list of all patients with the up-to-date information.
     */
    public static List<Patient> updatePatientInPatientList(Patient patientToUpdate, List<Patient> allPatients) {
        for (Patient patient1 : allPatients) {
            if (patient1.getId().equals(patientToUpdate.getId())) {
                patient1.updateAssignment(patientToUpdate.getAssignmentId());
                patient1.updateName(patientToUpdate.getName());
                patient1.updateContact(patientToUpdate.getContact());
                patient1.updateAddress(patientToUpdate.getAddress());
                patient1.updateDateOfBirth(patientToUpdate.getDateOfBirth());
                break;
            }
        }
        return allPatients;
    }
}
