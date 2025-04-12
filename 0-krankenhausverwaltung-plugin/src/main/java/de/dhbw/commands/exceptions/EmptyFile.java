package de.dhbw.commands.exceptions;

public class EmptyFile extends RuntimeException {
    public EmptyFile(String message) {
        super(message);
    }
}
