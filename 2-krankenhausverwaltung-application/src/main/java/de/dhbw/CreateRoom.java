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

    public CreateRoom(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public UUID execute(String building, String floor, String rooNumber, int roomSize) {
        // Check if there is already a room with the same address.
        RoomAddress roomAddress = new RoomAddress(building, floor, rooNumber);
        Room roomWithSameAddress = roomRepository.findRoomByRoomAddress(roomAddress);
        if (roomWithSameAddress != null) {
            throw new IllegalArgumentException("There is already a room with that address:" + roomAddress + " .");
        }

        Room room = new Room.RoomBuilder(UUID.randomUUID(), roomAddress, roomSize)
                .build();

        roomRepository.saveRoom(room);
        return room.getId();
    }
}
