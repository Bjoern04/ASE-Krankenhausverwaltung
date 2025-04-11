package de.dhbw;

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;

import java.util.List;
import java.util.UUID;

public class ReadDoctor {
    private final DoctorRepository doctorRepository;

    public ReadDoctor(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> execute(boolean all, UUID doctorId) {
        if (all) {
            List<Doctor> allDoctors = doctorRepository.loadDoctors();
            if (allDoctors == null) {
                throw new IllegalArgumentException("There was an error while loading the doctors. Please check the file with the doctors.");
            }
            return allDoctors;
        }
        else {
            Doctor doctor = doctorRepository.findDoctorById(doctorId);
            if (doctor == null) {
                throw new IllegalArgumentException("The doctor with the ID " + doctorId + " could not found.");
            }
            return List.of(doctor);
        }
    }
}
