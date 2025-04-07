package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.room.entity.Room;
import de.dhbw.room.repository.RoomRepository;
import de.dhbw.shared.RoomAddress;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RoomStorage implements RoomRepository {
    private final File file;
    private final JsonSerializer serializer;
    //private List<Room> rooms;

    public RoomStorage(String filePath) {
        this.file = new File(filePath);
        this.serializer = new JsonSerializer();
    }


    @Override
    public Room findRoomById(UUID id) {
        try {
            List<Room> rooms = loadRooms();
            return rooms.stream().filter(room -> room.getId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Room findRoomByRoomAddress(RoomAddress roomAddress) {
        try {
            List<Room> rooms = loadRooms();
            return rooms.stream()
                    .filter(room -> room.getRoomAddress().equals(roomAddress))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveRoom(Room room) {
        serializer.serializeUpdateFile(Collections.singletonList(room), file.getAbsolutePath());
        return true;
    }

    @Override
    public boolean deleteRoom(Room room) {
        return false;
    }

    @Override
    public void updateRoom(Room room) {
         try {
            List<Room> rooms = loadRooms();
            for (Room room1 : rooms) {
                if (room1.getId().equals(room.getId())) {
                    room1.updateAssignments(room.getAssignmentIds());
                    serializer.serializeOverwrite(rooms, file.getAbsolutePath());
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAssigment(Room room, UUID assignmentId) {

    }

    private List<Room> loadRooms() throws IOException {
        if (file.exists()) {
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Room.class);
            }
        } else {
            return new ArrayList<>();
        }
    }

    private void saveRooms(List<Room> rooms) throws IOException {
        serializer.serializeUpdateFile(rooms, file.getAbsolutePath());
    }
}

