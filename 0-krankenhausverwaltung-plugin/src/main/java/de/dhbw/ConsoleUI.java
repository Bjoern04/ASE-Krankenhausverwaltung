package de.dhbw;

import de.dhbw.commands.Command;
import de.dhbw.commands.CreateRoomCommand;
import de.dhbw.commands.exceptions.InvalidKeyword;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;

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
            } catch (InvalidKeyword e) {
                System.out.println("Fehler: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Command parseCommand(String input) throws InvalidKeyword {
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
