package de.dhbw.mixin.entities.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.aggregates.room.value_objects.RoomAddress;

import java.util.List;
import java.util.UUID;

public abstract class RoomBuilderMixin {
    @JsonCreator
    public RoomBuilderMixin (@JsonProperty("id") UUID id, @JsonProperty("roomAddress") RoomAddress roomAddress, @JsonProperty("roomSize") int roomSize) {

    }

    @JsonProperty("assignmentIds")
    public abstract Room.RoomBuilder withAssignments(List<UUID> assignmentIds);
}
