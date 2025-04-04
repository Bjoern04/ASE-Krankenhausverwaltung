package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.assignment.repository.AssignmentRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.room.entity.Room;

import java.util.List;
import java.util.UUID;

public class AssignmentStorage implements AssignmentRepository {
    @Override
    public Room findRoomForPatient(Patient patient) {
        return null;
    }

    @Override
    public List<Patient> findPatientsForRoom(Room room) {
        return List.of();
    }

    @Override
    public Assignment findAssignmentById(UUID id) {
        return null;
    }

    @Override
    public Assignment findAssignmentByPatient(Patient patient) {
        return null;
    }

    @Override
    public List<Assignment> findAssignmentsForRoom(Room room) {
        return List.of();
    }

    @Override
    public boolean saveAssigment(Assignment assignment) {
        return false;
    }

    @Override
    public boolean deleteAssigment(Assignment assignment) {
        return false;
    }

    @Override
    public void updateAssigment(Assignment assignment) {

    }

    @Override
    public List<Assignment> loadAssigments() throws Exception {
        return List.of();
    }
}
