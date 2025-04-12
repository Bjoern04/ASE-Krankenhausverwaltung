package de.dhbw.storage;

import de.dhbw.JsonSerializer;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.repository.RoomRepository;
import de.dhbw.aggregates.room.value_objects.RoomAddress;
import de.dhbw.commands.exceptions.EmptyFile;

import java.io.File;
import java.io.FileNotFoundException;
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
    public Room findRoomById(UUID id) throws FileNotFoundException {
        List<Room> rooms = loadRooms();
        return rooms.stream().filter(room -> room.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Room findRoomByRoomAddress(RoomAddress roomAddress) throws FileNotFoundException {
        List<Room> rooms = loadRooms();
        return rooms.stream()
                .filter(room -> room.getRoomAddress().equals(roomAddress))
                .findFirst()
                .orElse(null);
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
    public void updateRoom(Room room) throws FileNotFoundException {
        List<Room> rooms = loadRooms();
        for (Room room1 : rooms) {
            if (room1.getId().equals(room.getId())) {
                room1.updateAssignments(room.getAssignmentIds());
                serializer.serializeOverwrite(rooms, file.getAbsolutePath());
                break;
            }
        }
    }


    @Override
    public void deleteAssigment(Room room, UUID assignmentId) {

    }

    @Override
    public List<Room> loadRooms() throws FileNotFoundException {
        if (file.exists()) {
            if (file.length() == 0) {
                throw new EmptyFile("The file " + file.getAbsolutePath() + " is empty.");
            }
            else {
                return serializer.deserialize(file.getAbsolutePath(), Room.class);
            }
        }
        else {
            throw new FileNotFoundException("The file " + file.getAbsolutePath() + " does not exist.");
        }
    }

    private void saveRooms(List<Room> rooms) throws IOException {
        serializer.serializeUpdateFile(rooms, file.getAbsolutePath());
    }
}

