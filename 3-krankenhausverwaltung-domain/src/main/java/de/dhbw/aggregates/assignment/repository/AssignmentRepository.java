package de.dhbw.aggregates.assignment.repository;

import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.patient.entity.Patient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


public interface AssignmentRepository {
    Room findRoomForPatient(Patient patient);

    List<UUID> findPatientsForRoom (Room room) throws FileNotFoundException;

    Assignment findAssignmentById(UUID id) throws FileNotFoundException;

    Assignment findAssignmentByPatient(Patient patient);

    List<Assignment> findAssignmentsForRoom(Room room) throws FileNotFoundException;

    boolean saveAssignment(Assignment assignment);

    void deleteAssignment(UUID assignmentId) throws FileNotFoundException;

    void updateAssigment (Assignment assignment);

    List<Assignment> loadAssignments() throws FileNotFoundException;
}
