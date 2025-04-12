package de.dhbw.commands;

import de.dhbw.InputParser;
import de.dhbw.ReadPatient;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.PatientStorage;

import java.util.List;
import java.util.UUID;

public class ReadPatientCommand implements Command {
    private final String argument;

    public ReadPatientCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1){
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There has to be one parameter but there were " + arguments.size() + " entered.");
        }

        boolean all;
        UUID patientId = null;
        try {
            String input = (String) arguments.getFirst();
            if (input.equals("all")) {
                all = true;
            }
            else {
                patientId = UUID.fromString(input);
                all = false;
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("The input is invalid. Please use a valid UUID or 'all' to get all patients.");
        }

        ReadPatient readPatient = new ReadPatient(new PatientStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\patients.json"));
        List<Patient> patients = readPatient.execute(all, patientId);

        StringBuilder result = new StringBuilder();
        for (Patient patient : patients) {
            result.append(patient.toString()).append("\n");
        }

        // Remove the last newline character if it exists
        if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
