package de.dhbw.commands.read;

import de.dhbw.InputParser;
import de.dhbw.commands.Command;
import de.dhbw.use_cases.read.ReadAssignment;
import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.AssignmentStorage;

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
        // Remove the last newline character if it exists
        if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
