package de.dhbw;

import de.dhbw.commands.Command;
import de.dhbw.commands.InputParser;

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
                Command command = InputParser.parseCommand(input);
                if (command != null) {
                    String output = command.execute();
                    System.out.println(output);
                }
            } catch (RuntimeException exception) {
                System.out.println(ANSI_ORANGE +  exception.getClass().getSimpleName() +": " +  exception.getMessage() + ANSI_RESET);
            }
        }
    }
}
