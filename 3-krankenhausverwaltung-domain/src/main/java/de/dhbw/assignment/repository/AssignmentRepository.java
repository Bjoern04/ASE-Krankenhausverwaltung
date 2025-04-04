package de.dhbw.assignment.repository;

import de.dhbw.room.entity.Room;
import de.dhbw.patient.entity.Patient;

import java.util.List;


public interface AssignmentRepository {
    Room findRoomForPatient(Patient patient);

    List<Patient> findPatientsForRoom (Room room);
}
