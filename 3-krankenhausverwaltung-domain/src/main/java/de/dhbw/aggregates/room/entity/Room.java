package de.dhbw.aggregates.room.entity;

import de.dhbw.aggregates.room.value_objects.RoomAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            throw new NullPointerException("The ID of the room must not be null.");
        }
        this.id = builder.id;

        if (builder.roomAddress == null) {
            throw new NullPointerException("The address of the room must not be null.");
        }
        this.roomAddress = builder.roomAddress;

        if (builder.roomSize <= 0) {
            throw new IllegalArgumentException("The room size must be greater than zero.");
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
            throw new NullPointerException("The address of the room must not be null.");
        }
        this.roomAddress = roomAddress;
    }

    public void updateRoomSize(int roomSize) {
        if (roomSize <= 0) {
            throw new IllegalArgumentException("The room size must be greater than zero.");
        }
        this.roomSize = roomSize;
    }

    public void addAssignment (UUID assignmentId) {
        if (assignmentId == null) {
            throw new NullPointerException("The ID assignmentId must not be null.");
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

    @Override
    public String toString() {
        return "Room{" +
                "ID: " + id +
                ", RoomAddress: " + roomAddress +
                ", RoomSize: " + roomSize +
                ", AssignmentIds: [" +
                (assignmentIds != null ? assignmentIds.stream()
                        .map(UUID::toString)
                        .collect(Collectors.joining(", ")) : "null") +
                "]" +
                '}';
    }

    public static class RoomBuilder {
        private final UUID id;

        private final RoomAddress roomAddress;

        private final int roomSize;

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
