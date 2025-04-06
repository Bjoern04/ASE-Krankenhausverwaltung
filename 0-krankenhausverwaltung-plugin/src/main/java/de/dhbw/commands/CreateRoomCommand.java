package de.dhbw.commands;

import de.dhbw.storage.AssignmentStorage;
import de.dhbw.CreateRoom;
import de.dhbw.storage.RoomStorage;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import java.util.List;
import java.util.UUID;

public class CreateRoomCommand implements Command {
    private final String argument;

    public CreateRoomCommand(String argument) {
        this.argument = argument;
    }
    @Override
    public String execute() throws RuntimeException {
        CreateRoom createRoom = new CreateRoom(new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"), new AssignmentStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\assignments.json"));

        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 5) {
            throw new WrongAmoutOfParameters("Falsche Anzahl an Parameter. Es wurden fünf erwartet, eingegeben wurden aber:" + arguments.size());
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
