package de.dhbw.mixin.entities.room;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.dhbw.aggregates.room.entity.Room;

@JsonDeserialize(builder = Room.RoomBuilder.class)
    public abstract class RoomMixin {

}
