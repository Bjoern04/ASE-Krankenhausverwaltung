package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.assignment.repository.AssignmentRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.room.entity.Room;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AssignmentStorage implements AssignmentRepository {
    private final File file;
    private final JsonSerializer serializer;

    public AssignmentStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }

    @Override
    public Room findRoomForPatient(Patient patient) {
        return null;
    }

    @Override
    public List<UUID> findPatientsForRoom(Room room) {
        try {
            List<Assignment> assignmentIds = loadAssignments();
            return assignmentIds.stream().filter(assigment -> assigment.getId().equals(room.getId())).map(Assignment::getPatientId).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Assignment findAssignmentById(UUID id) {
        try {
            List<Assignment> assignmentIds = loadAssignments();
            return assignmentIds.stream().filter(room -> room.getId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Assignment findAssignmentByPatient(Patient patient) {
        return null;
    }

    @Override
    public List<Assignment> findAssignmentsForRoom(Room room) {
        try {
            List<Assignment> assignmentIds = loadAssignments();
            return assignmentIds.stream().filter(assigment -> assigment.getId().equals(room.getId())).collect(Collectors.toList());
        }
        catch  (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean saveAssignment(Assignment assignment) {
        serializer.serializeUpdateFile(Collections.singletonList(assignment), file.getAbsolutePath());
        return true;
    }

    @Override
    public void deleteAssignment(UUID assignmentId) {
        try {
            List<Assignment> assignmentIds = loadAssignments();
            Optional<Assignment> assignmentToDelete = assignmentIds.stream().filter(assignment -> assignment.getId().equals(assignmentId)).findFirst();

            if (assignmentToDelete.isPresent()) {
                assignmentIds.remove(assignmentToDelete.get());
                serializer.serializeUpdateFile(assignmentIds, file.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAssigment(Assignment assignment) {

    }

    private List<Assignment> loadAssignments() throws IOException {
        if (file.exists()) {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Assignment.class);
            }
        } else {
            return new ArrayList<>();
        }
    }
}
