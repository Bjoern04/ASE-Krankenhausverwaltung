package de.dhbw.aggregates.assignment.repository;

import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.patient.entity.Patient;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface AssignmentRepository {
    Room findRoomForPatient(Patient patient);

    List<UUID> findPatientsForRoom (Room room);

    Assignment findAssignmentById(UUID id);

    Assignment findAssignmentByPatient(Patient patient);

    List<Assignment> findAssignmentsForRoom(Room room);

    boolean saveAssignment(Assignment assignment);

    void deleteAssignment(UUID assignmentId);

    void updateAssigment (Assignment assignment);

    List<Assignment> loadAssignments();
}
