package de.dhbw;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.patient.entity.Patient;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;
import de.dhbw.shared.RoomAddress;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomStorage implements RoomRepository {
    private final File file;
    private final JsonSerializer serializer;
    private List<Room> rooms;

    public RoomStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }


    @Override
    public Room findRoomById(UUID id) {
        try {
            loadRooms();
            return rooms.stream().filter(room -> room.getId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Room findRoomByRoomAddress(RoomAddress roomAddress) {
        return null;
    }

    @Override
    public boolean saveRoom(Room room) {
        serializer.serialize(room, file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deleteRoom(Room room) {
        return false;
    }

    @Override
    public void updateRoom(Room room) {
        try {
            loadRooms();
            int index = rooms.indexOf(findRoomById(room.getId()));
            if (index >= 0) {
                rooms.set(index, room);
                saveRooms();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAssigment(Room room, UUID assignmentId) {

    }

    private void loadRooms() throws IOException {
        if (file.exists()) {
            rooms = serializer.deserialize(file.getAbsolutePath(), Room.class);
        } else {
            rooms = new ArrayList<>();
        }
    }

    private void saveRooms() throws IOException {
        serializer.serialize(rooms, file.getAbsolutePath());
    }
}

