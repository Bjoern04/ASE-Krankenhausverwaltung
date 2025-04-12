package de.dhbw.use_cases.create;

import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.repository.RoomRepository;
import de.dhbw.aggregates.room.value_objects.RoomAddress;

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
