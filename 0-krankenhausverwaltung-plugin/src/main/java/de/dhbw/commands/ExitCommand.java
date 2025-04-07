package de.dhbw.commands;

public class ExitCommand implements Command {
    @Override
    public String execute() throws RuntimeException {
        return "Program was terminated.";
    }
}
