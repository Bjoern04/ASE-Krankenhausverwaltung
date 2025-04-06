package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.assignment.repository.AssignmentRepository;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class AssignRoomToPatientUseCase {
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;
    private final AssignmentRepository assignmentRepository;

    public AssignRoomToPatientUseCase(PatientRepository patientRepository, RoomRepository roomRepository, AssignmentRepository assignmentRepository) {
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public void execute(UUID patientId, UUID roomId, LocalDate dateOfAdmission, LocalDate dateOfDischarge) throws Exception {
        // Check if the patient exists
        Patient patientToUpdate = patientRepository.findPatientById(patientId);
        if (patientToUpdate == null) {
            throw new IllegalArgumentException("Der von Ihnen übergebene Patient existiert nicht. Ein Patient muss zuerst im Krankenhaus registriert werden bevor er einem Zimmer zugewiesen werden kann.");
        }

        // Check if the room exists
        Room roomToUpdate = roomRepository.findRoomById(roomId);
        if (roomToUpdate == null) {
            throw new IllegalArgumentException("Der Raum dem der Patient hinzugefügt werden sollte existiert nicht. Bitte weisen Sie den Patienten einem existierenden Zimmer zu.");
        }

        // Check if the room has a bed available
        List<UUID> assignmentIdsOfTheRoom = roomToUpdate.getAssignmentIds();
        int availableBeds = roomToUpdate.getRoomSize() - assignmentIdsOfTheRoom.size();
        if (availableBeds <= 0) {
            throw new IllegalArgumentException("Der Raum ist voll. Bitte weisen Sie den Patienten einem anderen Raum zu.");
        }

        // Check if the patient is already assigned to a room
        UUID oldAssignmentId = patientToUpdate.getAssignmentId();

        if (oldAssignmentId == null) {
            // Patient was not assigned to any room before
            Assignment assignment = new Assignment.AssignmentBuilder(UUID.randomUUID(), roomId, patientId, dateOfAdmission).withDateOfDischarge(dateOfDischarge).build();
            assignmentRepository.saveAssigment(assignment);
            updateCorrespondingObjects(patientToUpdate, roomToUpdate, assignment);
        }
        else {
            // Patient was assigned to a room before
            Assignment oldAssignment = assignmentRepository.findAssignmentById(oldAssignmentId);
            if (oldAssignment == null) {
                throw new NoSuchElementException("Ein Assignment mit der im Patienten hinterlegten ID, konnte nicht gefunden werden. Bitte überprüfen Sie die JSON Datei.");
            }
            Room oldRoom = roomRepository.findRoomById(oldAssignmentId);
            if (oldRoom == null) {
                throw new NoSuchElementException("Ein Raum mit der im Assignment hinterlegten ID, konnte nicht gefunden werden. Bitte überprüfen Sie die JSON Datei.");
            }
            roomRepository.deleteAssigment(oldRoom, oldAssignmentId);
            Assignment assignment = new Assignment.AssignmentBuilder(oldAssignmentId, roomId, patientId, dateOfAdmission).withDateOfDischarge(dateOfDischarge).build();
            assignmentRepository.updateAssigment(assignment);
            updateCorrespondingObjects(patientToUpdate, roomToUpdate, assignment);
        }
    }

    private void updateCorrespondingObjects(Patient patientToUpdate, Room roomToUpdate, Assignment assignment) {
        patientToUpdate.updateAssignment(assignment.getId());
        roomToUpdate.addAssignment(assignment.getId());
        patientRepository.updatePatient(patientToUpdate);
        roomRepository.updateRoom(roomToUpdate);
    }
}
