package de.dhbw.commands;

import de.dhbw.InputParser;
import de.dhbw.ReadAssignment;
import de.dhbw.ReadDoctor;
import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.AssignmentStorage;
import de.dhbw.storage.DoctorStorage;

import java.util.List;
import java.util.UUID;

public class ReadAssignmentCommand implements Command {
    private final String argument;

    public ReadAssignmentCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1){
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There has to be one parameter but there were " + arguments.size() + " entered.");
        }

        boolean all;
        UUID assignmentId = null;
        try {
            String input = (String) arguments.getFirst();
            if (input.equals("all")) {
                all = true;
            }
            else {
                assignmentId = UUID.fromString(input);
                all = false;
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("The input is invalid. Please use a valid UUID or 'all' to get all assignments.");
        }

        ReadAssignment readAssignment = new ReadAssignment(new AssignmentStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\assignments.json"));
        List<Assignment> assignments = readAssignment.execute(all, assignmentId);

        StringBuilder result = new StringBuilder();
        for (Assignment assignment : assignments) {
            result.append(assignment.toString()).append("\n");
        }

        return result.toString();
    }
}
