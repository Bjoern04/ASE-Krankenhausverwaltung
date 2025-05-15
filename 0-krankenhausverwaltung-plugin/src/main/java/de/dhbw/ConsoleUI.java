package de.dhbw;

import de.dhbw.commands.Command;
import de.dhbw.commands.ExitCommand;
import de.dhbw.commands.HelpCommand;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Welcome to the hospital management system.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            try {
                Command command = InputParser.parseCommand(input);
                if (command != null) {
                    if (command.getClass().equals(ExitCommand.class)) {
                        String output = command.execute();
                        System.out.println(output);
                        break;
                    }
                    else if (command.getClass().equals(HelpCommand.class)) {
                        String output = command.execute();
                        System.out.println(output);
                    }
                    else {
                        String output = command.execute();
                        System.out.println(output);
                    }
                }
            }
            catch (RuntimeException | FileNotFoundException exception) {
                System.out.println(ANSI_ORANGE +  exception.getClass().getSimpleName() +": " +  exception.getMessage() + ANSI_RESET);
            }
        }
    }
}
