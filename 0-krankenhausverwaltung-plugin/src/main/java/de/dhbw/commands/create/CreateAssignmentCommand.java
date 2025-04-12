package de.dhbw.commands.create;

import de.dhbw.commands.Command;
import de.dhbw.use_cases.create.CreateAssignment;
import de.dhbw.InputParser;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.AssignmentStorage;
import de.dhbw.storage.PatientStorage;
import de.dhbw.storage.RoomStorage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateAssignmentCommand implements Command {
    private final String argument;

    public CreateAssignmentCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException, FileNotFoundException {
        CreateAssignment createAssignment = new CreateAssignment(new AssignmentStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\assignments.json"), new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"), new PatientStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\patients.json"));
        List<Object> arguments = InputParser.parseArguments(argument);

        if (arguments.size() < 3 || arguments.size() > 4) {
            throw new WrongAmoutOfParameters("Wrong amount of parameters. At least three parameters are required, but " + arguments.size() + " were given.\n");
        }

        UUID roomId;
        try {
            roomId = UUID.fromString(arguments.get(0).toString());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Room ID is invalid. Please use a valid UUID.");
        }

        UUID patientId;
        try {
            patientId = UUID.fromString(arguments.get(1).toString());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Patient ID is invalid. Please use a valid UUID.");
        }

        LocalDate dateOfAdmission = InputParser.parseLocalDate(arguments.get(2).toString());
        LocalDate dateOfDischarge = arguments.size() > 3 ? InputParser.parseLocalDate(arguments.get(3).toString()): null;

        UUID assigmentId = createAssignment.execute(roomId, patientId, dateOfAdmission, dateOfDischarge);
        return "The assigment was successfully created. The ID of the assigment is: " + assigmentId.toString();
    }
}
