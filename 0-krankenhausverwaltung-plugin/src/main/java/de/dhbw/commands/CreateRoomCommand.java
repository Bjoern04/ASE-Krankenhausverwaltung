package de.dhbw.commands;

import de.dhbw.CreateRoom;
import de.dhbw.RoomStorage;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.commands.exceptions.TooFewParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateRoomCommand implements Command {
    private String argument;

    public CreateRoomCommand(String argument) {
        this.argument = argument;
    }
    @Override
    public String execute() throws RuntimeException {
        CreateRoom createRoom = new CreateRoom(new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"));

        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 5) {
            throw new TooFewParameters("Too few parameters. Expected 5, but got " + arguments.size());
        }
        try {
            int building = Integer.parseInt(arguments.get(0).toString());
            int floor = Integer.parseInt(arguments.get(1).toString());
            int roomNumber = Integer.parseInt(arguments.get(2).toString());
            int roomSize = Integer.parseInt(arguments.get(3).toString());
            List<UUID> assignmentIds = InputParser.parseUuidList(arguments.get(4));

            UUID roomUUID = createRoom.execute(building, floor, roomNumber, roomSize, assignmentIds);
            return "The room was created successfully. The ID of the created room is: " + roomUUID.toString();

        } catch (NumberFormatException e) {
            throw new InvalidParameter("Invalid parameter for creating the room: " + e.getMessage());
        }
    }
}
