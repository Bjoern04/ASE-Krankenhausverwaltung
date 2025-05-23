package de.dhbw.commands.create;

import de.dhbw.InputParser;
import de.dhbw.commands.Command;
import de.dhbw.use_cases.create.CreateRoom;
import de.dhbw.storage.RoomStorage;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class CreateRoomCommand implements Command {
    private final String argument;

    public CreateRoomCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException, FileNotFoundException {
        CreateRoom createRoom = new CreateRoom(new RoomStorage(System.getProperty("user.dir") + "/" + "rooms.json"));

        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 4) {
            throw new WrongAmoutOfParameters("Falsche Anzahl an Parameter. Es wurden vier erwartet, eingegeben wurden aber:" + arguments.size());
        }
        String building = arguments.get(0).toString();
        String floor = arguments.get(1).toString();
        String roomNumber = arguments.get(2).toString();

        try {
            int roomSize = Integer.parseInt(arguments.get(3).toString());

            UUID roomUUID = createRoom.execute(building, floor, roomNumber, roomSize);
            return "The room was created successfully. The ID of the created room is: " + roomUUID.toString();

        } catch (NumberFormatException e) {
            throw new InvalidParameter("Invalid parameter for creating the room: " + e.getMessage());
        }
    }
}
