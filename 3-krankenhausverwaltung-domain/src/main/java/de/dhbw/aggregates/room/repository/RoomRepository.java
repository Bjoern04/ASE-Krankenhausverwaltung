package de.dhbw.aggregates.room.repository;

import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.value_objects.RoomAddress;

import java.util.UUID;

public interface RoomRepository {
    Room findRoomById (UUID id);

    Room findRoomByRoomAddress(RoomAddress roomAddress);

    boolean saveRoom (Room room);

    boolean deleteRoom (Room room);

    void updateRoom (Room room);

    void deleteAssigment(Room room, UUID assignmentId);
}
