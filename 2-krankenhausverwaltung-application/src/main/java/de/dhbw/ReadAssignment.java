package de.dhbw;

import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.assignment.repository.AssignmentRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;

import java.util.List;
import java.util.UUID;

public class ReadAssignment {
    private final AssignmentRepository assignmentRepository;

    public ReadAssignment(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<Assignment> execute(boolean all, UUID assignmentId) {
        if (all) {
            List<Assignment> allAssignments = assignmentRepository.loadAssignments();
            if (allAssignments == null) {
                throw new IllegalArgumentException("There was an error while loading the assignments. Please check the file with the assignments.");
            }
            System.out.println("allAssignments: " + allAssignments.size());
            return allAssignments;
        }
        else {
            Assignment assignment = assignmentRepository.findAssignmentById(assignmentId);
            if (assignment == null) {
                throw new IllegalArgumentException("The assignments with the ID " + assignmentId + " could not found.");
            }
            return List.of(assignment);
        }
    }
}
