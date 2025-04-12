package de.dhbw.commands;

import de.dhbw.commands.exceptions.InvalidKeyword;
import de.dhbw.commands.exceptions.InvalidParameter;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
    String execute() throws RuntimeException, FileNotFoundException;
}
