package de.dhbw.commands;

import de.dhbw.InputParser;
import de.dhbw.ReadPatient;
import de.dhbw.ReadRoom;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.PatientStorage;
import de.dhbw.storage.RoomStorage;

import java.util.List;
import java.util.UUID;

public class ReadRoomCommand implements Command{
    private final String argument;

    public ReadRoomCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1) {
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There has to be one parameter but there were " + arguments.size() + " entered.");
        }

        boolean all;
        UUID roomId = null;
        try {
            String input = (String) arguments.getFirst();
            if (input.equals("all")) {
                all = true;
            } else {
                roomId = UUID.fromString(input);
                all = false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("The input is invalid. Please use a valid UUID or 'all' to get all rooms.");
        }

        ReadRoom readRoom = new ReadRoom(new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"));
        List<Room> rooms = readRoom.execute(all, roomId);

        StringBuilder result = new StringBuilder();
        for (Room room : rooms) {
            result.append(room.toString()).append("\n");
        }
        return result.toString();
    }
}
