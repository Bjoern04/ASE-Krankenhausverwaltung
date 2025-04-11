package de.dhbw.commands;

import de.dhbw.InputParser;
import de.dhbw.ReadExamination;
import de.dhbw.ReadRoom;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.ExaminationStorage;
import de.dhbw.storage.RoomStorage;

import java.util.List;
import java.util.UUID;

public class ReadExaminationCommand implements Command {
    private final String argument;

    public ReadExaminationCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
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

        ReadExamination readExamination = new ReadExamination(new ExaminationStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\examinations.json"));
        List<Examination> examinations = readExamination.execute(all, examinationId);

        StringBuilder result = new StringBuilder();
        for (Examination examination : examinations) {
            result.append(examination.toString()).append("\n");
        }
        return result.toString();
    }
}
