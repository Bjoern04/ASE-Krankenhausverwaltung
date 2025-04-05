package de.dhbw;

import de.dhbw.commands.Command;
import de.dhbw.commands.CreateRoomCommand;
import de.dhbw.commands.exceptions.InvalidKeyword;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Willkommen in der Krankenhausverwaltung!");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            else if ("help".equalsIgnoreCase(input)) {
                System.out.println("Verfügbare Befehle: add, remove, list, exit");
                continue;
            }
            else if (input.isEmpty()) {
                continue;
            }
            try {
                Command command = parseCommand(input);
                if (command != null) {
                    String output = command.execute();
                    System.out.println(output);
                }
            } catch (RuntimeException exception) {
                System.out.println(ANSI_ORANGE +  exception.getClass().getSimpleName() +": " +  exception.getMessage() + ANSI_RESET);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Command parseCommand(String input) throws RuntimeException {
        String[] tokens = input.split(":");
        if (tokens.length != 2) {
            throw new InvalidKeyword("Ungültiger Befehl. Bitte geben Sie einen gültigen Befehl ein.");
        }
        tokens[0] = tokens[0].trim();
        tokens[0] = tokens[0].replace(" ", "");
        tokens[0] = tokens[0].toLowerCase();

        switch (tokens[0]) {
            case "createroom":
                return new CreateRoomCommand(tokens[1]);
        }
        return null;
    }
}
