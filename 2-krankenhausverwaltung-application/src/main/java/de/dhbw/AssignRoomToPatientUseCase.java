package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.patient.entity.Patient;
import de.dhbw.patient.repository.PatientRepository;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AssignRoomToPatientUseCase {
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;

    public AssignRoomToPatientUseCase(PatientRepository patientRepository, RoomRepository roomRepository) {
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
    }

    public void execute(UUID patient, UUID room, LocalDate dateOfAdmission, LocalDate dateOfDischarge) throws Exception {

        patientRepository.loadPatients();
        /*
        // Check if the patient exists
        Patient patientToUpdate = patientRepository.findPatientById(patient.getId());
        if (patientToUpdate == null) {
            throw new IllegalArgumentException("Der von Ihnen übergebene Patient existiert nicht. Ein Patient muss zuerst im Krankenhaus registriert werden bevor er einem Zimmer zugewiesen werden kann.");
        }

        // Check if the room exists
        Room roomToUpdate = roomRepository.findRoomById(room.getId());
        if (roomToUpdate == null) {
            throw new IllegalArgumentException("Der Raum dem der Patient hinzugefügt werden sollte existiert nicht. Bitte weisen Sie den Patienten einem existierenden Zimmer zu.");
        }

        // Check if the room has a bed available
        List<Assignment> assignmentsOfTheRoom = roomToUpdate.getAssignments();
        int availableBeds = roomToUpdate.getRoomSize() - assignmentsOfTheRoom.size();
        if (availableBeds <= 0) {
            throw new IllegalArgumentException("Der Raum ist voll. Bitte weisen Sie den Patienten einem anderen Raum zu.");
        }

        // Check if the patient is already assigned to a room
        boolean patientAssignedToRoom = false;
        Assignment oldAssignment = patient.getAssignment();
        for (Assignment assignment : assignmentsOfTheRoom) {
            if (assignment.equals(oldAssignment)) {
                patientAssignedToRoom = true;
                break;
            }
        }

        if (!patientAssignedToRoom) {
            Assignment assignment = new Assignment.AssignmentBuilder(UUID.randomUUID(), room, patient, dateOfAdmission).withDateOfDischarge(dateOfDischarge).build();

            // Delete old assignment if it exists
            if (oldAssignment != null) {
                Room oldRoom = roomRepository.findRoomById(oldAssignment.getRoom().getId());

                if (oldRoom != null) {
                    roomRepository.deleteAssigment(oldAssignment);
                    roomRepository.updateRoom(oldRoom);
                }
            }

            // Update the patient and room with the new assignment
            patient.updateAssignment(assignment);
            roomToUpdate.addAssignment(assignment);
            patientRepository.updatePatient(patient);
            roomRepository.updateRoom(roomToUpdate);
        }
*/

    }
}
