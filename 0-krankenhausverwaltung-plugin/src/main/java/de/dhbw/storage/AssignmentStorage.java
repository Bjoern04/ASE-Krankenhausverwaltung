package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.assignment.repository.AssignmentRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.commands.exceptions.EmptyFile;

import java.io.File;
import java.io.FileNotFoundException;
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
    public Assignment findAssignmentById(UUID id) throws FileNotFoundException {
        List<Assignment> assignmentIds = loadAssignments();
        return assignmentIds.stream().filter(assignment -> assignment.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public boolean saveAssignment(Assignment assignment) {
        serializer.serializeUpdateFile(Collections.singletonList(assignment), file.getAbsolutePath());
        return true;
    }

    @Override
    public void deleteAssignment(UUID assignmentId) throws FileNotFoundException {
        List<Assignment> assignmentIds = loadAssignments();
        Optional<Assignment> assignmentToDelete = assignmentIds.stream().filter(assignment -> assignment.getId().equals(assignmentId)).findFirst();

        if (assignmentToDelete.isPresent()) {
            assignmentIds.remove(assignmentToDelete.get());
            serializer.serializeUpdateFile(assignmentIds, file.getAbsolutePath());
        }
    }

    @Override
    public List<Assignment> loadAssignments() throws FileNotFoundException {
        if (file.exists()) {
            if (file.length() == 0) {
               throw new EmptyFile("The file " + file.getAbsolutePath() + " is empty.");
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Assignment.class);
            }
        }
        else {
            throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist.");
        }
    }
}
