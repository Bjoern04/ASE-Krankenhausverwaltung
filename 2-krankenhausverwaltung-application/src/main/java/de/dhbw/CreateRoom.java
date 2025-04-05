package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.assignment.repository.AssignmentRepository;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;
import de.dhbw.shared.RoomAddress;

import java.util.List;
import java.util.UUID;

public class CreateRoom {
    private final RoomRepository roomRepository;
    private final AssignmentRepository assignmentRepository;

    public CreateRoom(RoomRepository roomRepository, AssignmentRepository assignmentRepository) {
        this.roomRepository = roomRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public UUID execute(int building, int floor, int rooNumber, int roomSize, List<UUID> assignmentIds) {
        // Check if the assignments exist.
        for (UUID assignmentId : assignmentIds) {
            Assignment assignment = assignmentRepository.findAssignmentById(assignmentId);
            if (assignment == null) {
                throw new IllegalArgumentException("Assignment mit der ID " + assignmentId + " existiert nicht.");
            }
        }

        // Check if there is already a room with the same address.
        RoomAddress roomAddress = new RoomAddress(building, floor, rooNumber);
        Room roomWithSameAddress = roomRepository.findRoomByRoomAddress(roomAddress);
        if (roomWithSameAddress != null) {
            throw new IllegalArgumentException("Ein Zimmer mit der Adresse " + roomAddress + " existiert bereits.");
        }

        Room room = new Room.RoomBuilder(UUID.randomUUID(), roomAddress, roomSize)
                .withAssignments(assignmentIds)
                .build();

        roomRepository.saveRoom(room);
        return room.getId();
    }
}
