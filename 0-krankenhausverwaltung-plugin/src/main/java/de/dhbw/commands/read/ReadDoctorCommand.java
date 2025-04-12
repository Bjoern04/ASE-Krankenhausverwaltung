package de.dhbw.commands.read;

import de.dhbw.InputParser;
import de.dhbw.commands.Command;
import de.dhbw.use_cases.read.ReadDoctor;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.DoctorStorage;

import java.util.List;
import java.util.UUID;

public class ReadDoctorCommand implements Command {
    private final String argument;

    public ReadDoctorCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        List<Object> arguments = InputParser.parseArguments(argument);
        if (arguments.size() != 1){
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There has to be one parameter but there were " + arguments.size() + " entered.");
        }

        boolean all;
        UUID doctorId = null;
        try {
            String input = (String) arguments.getFirst();
            if (input.equals("all")) {
                all = true;
            }
            else {
                doctorId = UUID.fromString(input);
                all = false;
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException("The input is invalid. Please use a valid UUID or 'all' to get all patients.");
        }

        ReadDoctor readDoctor = new ReadDoctor(new DoctorStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\doctors.json"));
        List<Doctor> doctors = readDoctor.execute(all, doctorId);

        StringBuilder result = new StringBuilder();
        for (Doctor doctor : doctors) {
            result.append(doctor.toString()).append("\n");
        }

        // Remove the last newline character if it exists
        if (!result.isEmpty() && result.charAt(result.length() - 1) == '\n') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
