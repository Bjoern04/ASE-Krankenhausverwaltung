package de.dhbw.commands.exceptions;

import de.dhbw.commands.Command;

import java.io.IOException;

public class InvalidKeyword extends RuntimeException {
    public InvalidKeyword(String message) {
        super(message);
    }

}
