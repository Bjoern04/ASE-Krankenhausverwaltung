package de.dhbw;

import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.assignment.repository.AssignmentRepository;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.aggregates.patient.util.PatientUpdater;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateAssignment {
    private final AssignmentRepository assignmentRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public CreateAssignment(AssignmentRepository assignmentRepository, RoomRepository roomRepository, PatientRepository patientRepository) {
        this.assignmentRepository = assignmentRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    public UUID execute(UUID roomId, UUID patientId, LocalDate dateOfAdmission, LocalDate dateOfDischarge) {
        // Find Room by ID
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("A room with the ID " + roomId + " does not exist.");
        }

        // Get Assignments and free space
        int freeSpace = room.getRoomSize() - room.getAssignmentIds().size();
        if (freeSpace == 0) {
            throw new IllegalArgumentException("There is no free space in this room.");
        }

        // Check if the patient exists
        Patient patient = patientRepository.findPatientById(patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " does not exist.");
        }

        // Create a new assignment
        Assignment assignment = new Assignment.AssignmentBuilder(UUID.randomUUID(), roomId, patientId, dateOfAdmission)
                .withDateOfDischarge(dateOfDischarge)
                .build();

        if(patient.getAssignmentId() != null) {
            //Get the old assignment
            Assignment oldAssignment = assignmentRepository.findAssignmentById(patient.getAssignmentId());
            if (oldAssignment == null) {
                throw new IllegalArgumentException("The old assignment does not exist. There must be an error in your json file.");
            }

            // Remove the old assignment from the old room and update the room
            Room oldRoom = roomRepository.findRoomById(oldAssignment.getRoomId());
            if (oldRoom == null) {
                throw new IllegalArgumentException("The old room does not exist. There must be an error in your json file.");
            }
            oldRoom.removeAssignment(oldAssignment.getId());
            roomRepository.updateRoom(oldRoom);

            // Delete the old assignment
            assignmentRepository.deleteAssignment(oldAssignment.getId());
        }

        // Create an updated version of the patient
        patient.updateAssignment(assignment.getId());
        List<Patient> patients = PatientUpdater.updatePatientInPatientList(patient, patientRepository.findAllPatients());
        patientRepository.updatePatient(patients);

        // Add the assignment to the room
        room.addAssignment(assignment.getId());
        roomRepository.updateRoom(room);

        // Save the assignment
        assignmentRepository.saveAssignment(assignment);
        return assignment.getId();
    }
}
