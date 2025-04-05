package de.dhbw.commands.exceptions;

public class WrongAmoutOfParameters extends RuntimeException {
    public WrongAmoutOfParameters(String message) {
        super(message);
    }
}
