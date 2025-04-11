package de.dhbw.commands;

import de.dhbw.ExaminationReassignment;
import de.dhbw.InputParser;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.DoctorStorage;
import de.dhbw.storage.ExaminationStorage;

import java.util.List;
import java.util.UUID;

public class ExaminationReassignmentCommand implements Command {
    private final String argument;

    public ExaminationReassignmentCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 2) {
            throw new WrongAmoutOfParameters("Wrong amount of parameters. There must be two parameters but " + arguments.size() + " were given.");
        }

        // Check examination and doctor ID
        UUID examinationId;
        UUID doctorId;
        try {
            examinationId = UUID.fromString(arguments.get(0).toString());
            doctorId = UUID.fromString(arguments.get(1).toString());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid parameter for the UUIDs: " + e.getMessage());
        }

        ExaminationReassignment examinationReassignment = new ExaminationReassignment(new ExaminationStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\examinations.json"), new DoctorStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\doctors.json"));
        return examinationReassignment.execute(examinationId, doctorId);
    }
}
