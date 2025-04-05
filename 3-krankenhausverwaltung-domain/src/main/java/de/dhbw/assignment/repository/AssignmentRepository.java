package de.dhbw.assignment.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.dhbw.assignment.entity.Assignment;
import de.dhbw.room.entity.Room;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.Name;

import java.util.List;
import java.util.UUID;


public interface AssignmentRepository {
    Room findRoomForPatient(Patient patient);

    List<Patient> findPatientsForRoom (Room room);

    Assignment findAssignmentById(UUID id);

    Assignment findAssignmentByPatient(Patient patient);

    List<Assignment> findAssignmentsForRoom(Room room);

    boolean saveAssigment(Assignment assignment);

    boolean deleteAssigment(Assignment assignment);

    void updateAssigment (Assignment assignment);
}
