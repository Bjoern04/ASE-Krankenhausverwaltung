package de.dhbw.use_cases.read;

import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.repository.RoomRepository;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class ReadRoom {
    private final RoomRepository roomRepository;

    public ReadRoom(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> execute(boolean all, UUID roomId) throws FileNotFoundException {
        if (all) {
            List<Room> allRooms = roomRepository.loadRooms();
            if (allRooms == null) {
                throw new IllegalArgumentException("There was an error while loading the rooms. Please check the file with the rooms.");
            }
            return allRooms;
        }
        else {
            Room room = roomRepository.findRoomById(roomId);
            if (room == null) {
                throw new IllegalArgumentException("The room with the ID " + roomId + " could not found.");
            }
            return List.of(room);
        }
    }
}
