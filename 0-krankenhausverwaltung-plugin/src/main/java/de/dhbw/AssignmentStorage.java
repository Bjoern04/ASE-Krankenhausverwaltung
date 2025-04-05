package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.assignment.repository.AssignmentRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.room.entity.Room;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AssignmentStorage implements AssignmentRepository {
    private final File file;
    private final JsonSerializer serializer;
    private List<Assignment> assignmentIds;

    public AssignmentStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }

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
        try {
            loadAssignments();
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

    private void loadAssignments() throws IOException {
        if (file.exists()) {
            if (file.length() == 0) {
                assignmentIds = new ArrayList<>();
            }
            else {
                assignmentIds = serializer.deserialize(file.getAbsolutePath(), Assignment.class);
            }
        } else {
            assignmentIds = new ArrayList<>();
        }
    }
}
