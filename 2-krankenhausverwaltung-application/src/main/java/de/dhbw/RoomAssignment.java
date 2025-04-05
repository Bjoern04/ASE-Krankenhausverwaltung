package de.dhbw;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.dhbw.assignment.entity.Assignment;
import de.dhbw.assignment.repository.AssignmentRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomAssignment {
    private final RoomRepository roomRepository;
    private final AssignmentRepository assignmentRepository;
    private final PatientRepository patientRepository;

    public RoomAssignment(RoomRepository roomRepository, AssignmentRepository assignmentRepository, PatientRepository patientRepository) {
        this.roomRepository = roomRepository;
        this.assignmentRepository = assignmentRepository;
        this.patientRepository = patientRepository;
    }

    public String execute(UUID roomId) {
        // Find Room by ID
        Room room = roomRepository.findRoomById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Ein Zimmer mit der ID " + roomId + " konnte nicht gefunden werden.");
        }

        // Get Assignments and free space
        int freeSpace = room.getRoomSize() - room.getAssignmentIds().size();
        List<UUID> assignments = assignmentRepository.findAssignmentsForRoom(room);
        if (assignments.isEmpty()) {
            return "Es ist kein Patient in diesem Zimmer. Es sind noch " + freeSpace + " Plätze frei.";
        }

        // Get the patientIds of the patients that are in the room
        List<UUID> patientIds = assignmentRepository.findPatientsForRoom(room);

        // Get the names of the patients
        List<Patient> patients = new ArrayList<>();
        for (UUID patientId : patientIds) {
            patients.add(patientRepository.findPatientById(patientId));
        }

        if (patients.isEmpty()) {
            return "Es ist kein Patient in diesem Zimmer. Es sind noch " + freeSpace + " Plätze frei.";
        }
        else {
            StringBuilder result = new StringBuilder();
            for (Patient patient : patients) {
                result.append(patient.getName().toString()).append("; ");
            }
            result.replace(result.length() - 2, result.length(), "");
            return "Die Patienten in diesem Zimmer sind: " + result.toString() + ". Es sind noch " + freeSpace + " Plätze frei.";
        }
    }
}
