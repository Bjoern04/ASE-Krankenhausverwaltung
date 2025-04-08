package de.dhbw.commands;

public class ExaminationReassignmentCommand implements Command {
    private final String argument;

    public ExaminationReassignmentCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {

        return "";
    }
}
