package de.dhbw.commands.exceptions;

public class InvalidParameter extends RuntimeException {
    public InvalidParameter(String message) {
        super(message);
    }
}
