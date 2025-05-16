package de.dhbw.aggregates.room.repository;

import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.value_objects.RoomAddress;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public interface RoomRepository {
    Room findRoomById (UUID id) throws FileNotFoundException;

    Room findRoomByRoomAddress(RoomAddress roomAddress) throws FileNotFoundException;

    boolean saveRoom (Room room);

    void updateRoom (Room room) throws FileNotFoundException;

    List<Room> loadRooms() throws FileNotFoundException;
}
