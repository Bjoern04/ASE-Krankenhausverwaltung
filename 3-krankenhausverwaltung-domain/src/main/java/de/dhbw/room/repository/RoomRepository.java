package de.dhbw.room.repository;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.room.entity.Room;
import de.dhbw.shared.RoomAddress;

import java.util.UUID;

public interface RoomRepository {
    Room findRoomById (UUID id);

    Room findRoomByRoomAddress(RoomAddress roomAddress);

    boolean saveRoom (Room room);

    boolean deleteRoom (Room room);

    void updateRoom (Room room);

    void deleteAssigment(Room room, UUID assignmentId);
}
