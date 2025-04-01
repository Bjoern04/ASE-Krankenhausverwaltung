package de.dhbw.room.entity;

import de.dhbw.assignment.entity.Assignment;
import de.dhbw.patient.entity.Patient;
import de.dhbw.shared.RoomAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Room entity representing a room in the Krankenhausverwaltung.
 */
public class Room {
    private final UUID id;

    private RoomAddress roomAddress;

    private int roomSize;

    private List<Assignment> assignments;

    private Room (RoomBuilder builder) {
        if (builder.id == null) {
            throw new NullPointerException("Die ID des Raumes darf nicht null sein.");
        }
        this.id = builder.id;

        if (builder.roomAddress == null) {
            throw new NullPointerException("Die Adresse des Raumes darf nicht null sein.");
        }
        this.roomAddress = builder.roomAddress;

        if (builder.roomSize <= 0) {
            throw new IllegalArgumentException("Die Raumgröße muss größer als null sein.");
        }
        this.roomSize = builder.roomSize;

        this.assignments = builder.assignments != null
                ? new ArrayList<>(builder.assignments)
                : new ArrayList<>();

    }

    // Getters
    public UUID getId() {
        return id;
    }

    public RoomAddress getRoomAddress() {
        return roomAddress;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void updateRoomAddress(RoomAddress roomAddress) {
        if (roomAddress == null) {
            throw new NullPointerException("Die Adresse des Raumes darf nicht null sein.");
        }
        this.roomAddress = roomAddress;
    }

    public void updateRoomSize(int roomSize) {
        if (roomSize <= 0) {
            throw new IllegalArgumentException("Die Raumgröße muss größer als null sein.");
        }
        this.roomSize = roomSize;
    }

    public void addAssignment (Assignment assignment) {
        if (assignment == null){
            throw new NullPointerException("Die Belegung darf nicht null sein.");
        }
        this.assignments.add(assignment);
    }

    public void updateAssignments(List<Assignment> assignments) {
        this.assignments.addAll(assignments);
    }

    public static class RoomBuilder {
        private final UUID id;

        private RoomAddress roomAddress;

        private int roomSize;

        private List<Assignment> assignments;

        public RoomBuilder (UUID id, RoomAddress roomAddress, int roomSize) {
            this.id = id;
            this.roomAddress = roomAddress;
            this.roomSize = roomSize;
        }

        public void withAssignments(List<Assignment> assignments) {
            this.assignments = assignments;
        }

        /**
         * Building a {@link Room}.
         *
         * @return Returns the created {@link Room} object.
         */
        public Room build() {
            return new Room(this);
        }
    }
}
