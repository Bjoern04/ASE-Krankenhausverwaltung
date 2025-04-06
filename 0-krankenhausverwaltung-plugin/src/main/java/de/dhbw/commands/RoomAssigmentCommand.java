package de.dhbw.commands;

import de.dhbw.storage.AssignmentStorage;
import de.dhbw.storage.PatientStorage;
import de.dhbw.RoomAssignment;
import de.dhbw.storage.RoomStorage;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;

import java.util.List;
import java.util.UUID;

public class RoomAssigmentCommand implements Command{
    private final String argument;

    public RoomAssigmentCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        RoomAssignment roomAssignment = new RoomAssignment(new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"), new AssignmentStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\assignments.json"), new PatientStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\patients.json"));

        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1) {
            throw new WrongAmoutOfParameters("Falsche Anzahl an Parameter. Es wurde einer erwartet, eingegeben wurden aber: " + arguments.size());
        }

        try {
            UUID id  = UUID.fromString(arguments.getFirst().toString().trim());
            return roomAssignment.execute(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameter("Ungültiger Parameter für eine UUID anhand der die Belegungen eines Zimmers gefunden werden sollen: " + e.getMessage());
        }
    }
}
