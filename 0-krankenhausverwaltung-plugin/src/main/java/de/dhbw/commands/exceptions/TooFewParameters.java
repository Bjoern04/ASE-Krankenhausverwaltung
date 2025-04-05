package de.dhbw.commands.exceptions;

public class TooFewParameters extends RuntimeException {
    public TooFewParameters(String message) {
        super(message);
    }
}
