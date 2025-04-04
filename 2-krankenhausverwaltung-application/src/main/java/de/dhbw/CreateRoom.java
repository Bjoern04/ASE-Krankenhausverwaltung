package de.dhbw;

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

    public UUID execute(int building, int floor, int rooNumber, int roomSize, List<UUID> assignments) {
        RoomAddress roomAddress = new RoomAddress(building, floor, rooNumber);
        Room room = new Room.RoomBuilder(UUID.randomUUID(), roomAddress, roomSize)
                .withAssignments(assignments)
                .build();

        roomRepository.saveRoom(room);
        return room.getId();
    }
}
