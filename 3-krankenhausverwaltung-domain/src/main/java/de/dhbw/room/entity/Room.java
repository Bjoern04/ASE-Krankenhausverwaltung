package de.dhbw.room.entity;

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

    private List<UUID> assignmentIds;

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

        this.assignmentIds = builder.assignmentIds != null
                ? new ArrayList<>(builder.assignmentIds)
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

    public List<UUID> getAssignmentIds() {
        return assignmentIds;
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

    public void addAssignment (UUID assignmentId) {
        if (assignmentId == null) {
            throw new NullPointerException("Die Belegung darf nicht null sein.");
        }
        if (!this.assignmentIds.contains(assignmentId)) {
            this.assignmentIds.add(assignmentId);
        }
    }

    public void removeAssignment(UUID assignmentId) {
        if (assignmentId == null) {
            throw new NullPointerException("The ID assignmentId must not be null.");
        }
        this.assignmentIds.remove(assignmentId);
    }

    public void updateAssignments(List<UUID> assignmentIds) {
        for (UUID assignmentId : assignmentIds) {
            if (!this.assignmentIds.contains(assignmentId)) {
                this.assignmentIds.add(assignmentId);
            }
        }
    }

    public static class RoomBuilder {
        private final UUID id;

        private RoomAddress roomAddress;

        private int roomSize;

        private List<UUID> assignmentIds;

        public RoomBuilder (UUID id, RoomAddress roomAddress, int roomSize) {
            this.id = id;
            this.roomAddress = roomAddress;
            this.roomSize = roomSize;
        }

        public RoomBuilder withAssignments(List<UUID> assignmentIds) {
            this.assignmentIds = assignmentIds;
            return this;
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
