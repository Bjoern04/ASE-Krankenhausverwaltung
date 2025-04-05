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
import java.util.stream.Collectors;

public class AssignmentStorage implements AssignmentRepository {
    private final File file;
    private final JsonSerializer serializer;
    //private List<Assignment> assignmentIds;

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
            return assignmentIds.stream().filter(assigment -> assigment.getId().equals(room.getId())).map(Assignment::getPatient).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Assignment findAssignmentById(UUID id) {
        try {
            List<Assignment> assignmentIds =loadAssignments();
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
    public List<UUID> findAssignmentsForRoom(Room room) {
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
