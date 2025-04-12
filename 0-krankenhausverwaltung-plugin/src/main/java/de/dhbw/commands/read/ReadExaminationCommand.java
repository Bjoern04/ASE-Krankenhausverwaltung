package de.dhbw.commands.read;

import de.dhbw.InputParser;
import de.dhbw.commands.Command;
import de.dhbw.use_cases.read.ReadExamination;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.ExaminationStorage;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

public class ReadExaminationCommand implements Command {
    private final String argument;

    public ReadExaminationCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException, FileNotFoundException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1) {
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There has to be one parameter but there were " + arguments.size() + " entered.");
        }

        boolean all;
        UUID examinationId = null;
        try {
            String input = (String) arguments.getFirst();
            if (input.equals("all")) {
                all = true;
            } else {
                examinationId = UUID.fromString(input);
                all = false;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("The input is invalid. Please use a valid UUID or 'all' to get all examinations.");
        }

        ReadExamination readExamination = new ReadExamination(new ExaminationStorage(System.getProperty("user.dir") + "/" + "examinations.json"));
        List<Examination> examinations = readExamination.execute(all, examinationId);

        StringBuilder result = new StringBuilder();
        for (Examination examination : examinations) {
            result.append(examination.toString()).append("\n");
        }

        // Remove the last newline character if it exists
        if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
