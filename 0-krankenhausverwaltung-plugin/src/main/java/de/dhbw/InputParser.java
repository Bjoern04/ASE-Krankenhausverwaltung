package de.dhbw;

import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.commands.*;
import de.dhbw.commands.create.*;
import de.dhbw.commands.exceptions.InvalidKeyword;
import de.dhbw.commands.read.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InputParser {

    public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm");

    public static Command parseCommand(String input) throws RuntimeException {
        String[] tokens = input.split("=>");
        if (tokens.length != 2) {
            if (tokens.length == 1) {
                if (tokens[0].trim().equalsIgnoreCase("exit")) {
                    return new ExitCommand();
                } else if (tokens[0].trim().equalsIgnoreCase("help")) {
                    return new HelpCommand();
                }
            }
            throw new InvalidKeyword("Invalid command format. Please enter a valid command.");
        }
        tokens[0] = tokens[0].trim();
        tokens[0] = tokens[0].replace(" ", "");
        tokens[0] = tokens[0].toLowerCase();

        switch (tokens[0]) {
            case "createroom":
                return new CreateRoomCommand(tokens[1]);

            case "createpatient":
                return new CreatePatientCommand(tokens[1]);

            case "createdoctor":
                return new CreateDoctorCommand(tokens[1]);

            case "createexamination":
                return new CreateExaminationCommand(tokens[1]);

            case "createassignment":
                return new CreateAssignmentCommand(tokens[1]);

            case "deletedoctor":
                return new DeleteDoctorCommand(tokens[1]);

            case "examinationreassignment":
                return new ExaminationReassignmentCommand(tokens[1]);

            case "readpatient":
                return new ReadPatientCommand(tokens[1]);

            case "readdoctor":
                return new ReadDoctorCommand(tokens[1]);

            case "readroom":
                return new ReadRoomCommand(tokens[1]);

            case "readexamination":
                return new ReadExaminationCommand(tokens[1]);

            case "readassignment":
                return new ReadAssignmentCommand(tokens[1]);

            case "showdoctorexaminationplan":
                return new ShowDoctorExaminationPlanCommand(tokens[1]);
        }
        return null;
    }

    public static List<Object> parseArguments(String argument) {
        List<Object> result = new ArrayList<>();
        String[] topLevelParameters = argument.split(",(?![^\\[]*\\])"); // Split by comma, ignoring commas inside brackets

        for (String param : topLevelParameters) {
            param = param.trim();
            if (param.startsWith("[") && param.endsWith("]")) {
                // Found an inner array
                String innerArrayContent = param.substring(1, param.length() - 1).trim();

                if (!innerArrayContent.isEmpty()) {
                    String[] innerArray = innerArrayContent.split(",");
                    for (int i = 0; i < innerArray.length; i++) {
                        innerArray[i] = innerArray[i].trim();
                    }
                    result.add(innerArray);
                } else {
                    result.add(new String[0]); // Empty inner array
                }
            } else {
                // Simple parameter
                result.add(param);
            }
        }
        return result;
    }

    public static List<UUID> parseUuidList(Object element) throws IllegalArgumentException {
        if (!(element instanceof String[])) {
            throw new IllegalArgumentException("The element is not a string array and cannot be parsed as a list of UUIDs.");
        }

        String[] uuidStrings = (String[]) element;
        List<UUID> uuidList = new ArrayList<>();

        for (String uuidStr : uuidStrings) {
            try {
                UUID uuid = UUID.fromString(uuidStr.trim());
                uuidList.add(uuid);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid UUID-format in Array: '" + uuidStr.trim() + "'", e);
            }
        }

        return uuidList;
    }

    public static LocalDate parseLocalDate(String input) throws IllegalArgumentException {
        try {
            return LocalDate.parse(input.trim(), LOCAL_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid LocalDate format. Expected format: " + LOCAL_DATE_FORMATTER, e);
        }
    }

    public static LocalDateTime parseLocalDateTime(String input) {
        try {
            return LocalDateTime.parse(input.trim(), LOCAL_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid LocalDateTime format. Expected format: " + LOCAL_DATE_TIME_FORMATTER, e);
        }
    }

    public static List<ExaminationType> parseExaminationTypeList(Object element) throws IllegalArgumentException {
        if (!(element instanceof String[])) {
            throw new IllegalArgumentException("Invalid element type. Expected String array.");
        }

        String[] typeStrings = (String[]) element;
        List<ExaminationType> typeList = new ArrayList<>();

        for (String typeStr : typeStrings) {
            try {
                ExaminationType type = ExaminationType.valueOf(typeStr.trim());
                typeList.add(type);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid Examination Type format in array: '" + typeStr.trim() + "'", e);
            }
        }

        return typeList;
    }

}
