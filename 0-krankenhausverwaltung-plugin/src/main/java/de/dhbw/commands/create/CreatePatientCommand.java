package de.dhbw.commands.create;

import de.dhbw.commands.Command;
import de.dhbw.use_cases.create.CreatePatient;
import de.dhbw.InputParser;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.storage.PatientStorage;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreatePatientCommand implements Command {
    private final String argument;

    public CreatePatientCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        CreatePatient createPatient = new CreatePatient(new PatientStorage(System.getProperty("user.dir") + "/" + "patients.json"));
        List<Object> arguments = InputParser.parseArguments(argument);
        System.out.println("Arguments: " + arguments.size());

        try {
            String firstName = arguments.size() > 0 ? arguments.get(0).toString() : null;
            String lastName = arguments.size() > 1 ? arguments.get(1).toString(): null;
            String street = arguments.size() > 2 ? arguments.get(2).toString(): null;
            String hoseNumber = arguments.size() > 3 ? arguments.get(3).toString(): null;
            String zipCode = arguments.size() > 4 ? arguments.get(4).toString(): null;
            String city = arguments.size() > 5 ? arguments.get(5).toString(): null;
            LocalDate birthDate = arguments.size() > 6 ? InputParser.parseLocalDate(arguments.get(6).toString()): null;
            String phoneNumber = arguments.size() > 7 ? arguments.get(7).toString(): null;
            String email = arguments.size() > 8 ? arguments.get(8).toString(): null;

            System.out.println("phoneNumber: " + phoneNumber);
            System.out.println("Email: " + email);

            UUID patientUUID = createPatient.execute(firstName, lastName, street, hoseNumber, zipCode, city, birthDate, phoneNumber, email);
            return "The patient was created successfully. The patient ID is: " + patientUUID.toString();
        }
        catch (NullPointerException exception) {
            throw new InvalidParameter("Invalid parameter for creating the patient: " + exception.getMessage());
        }
    }
}
