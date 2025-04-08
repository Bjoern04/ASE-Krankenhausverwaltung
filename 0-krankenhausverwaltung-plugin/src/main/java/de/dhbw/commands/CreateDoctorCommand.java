package de.dhbw.commands;

import de.dhbw.CreateDoctor;
import de.dhbw.InputParser;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.DoctorStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateDoctorCommand implements Command {
    private final String argument;

    public CreateDoctorCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        CreateDoctor createDoctor = new CreateDoctor(new DoctorStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\doctors.json"));
        List<Object> arguments = InputParser.parseArguments(argument);

        if (arguments.size() < 9 || arguments.size() > 10) {
            throw new WrongAmoutOfParameters("Wrong amount of parameters. There should be nine or ten parameters, but:" + arguments.size() + " were entered.\n");
        }

        String firstName = arguments.get(0).toString();
        String lastName = arguments.get(1).toString();
        String street = arguments.get(2).toString();
        String hoseNumber = arguments.get(3).toString();
        String zipCode = arguments.get(4).toString();
        String city = arguments.get(5).toString();
        LocalDate birthDate = InputParser.parseLocalDate(arguments.get(6).toString());
        String phoneNumber = arguments.get(7).toString();
        String email = arguments.get(8).toString();
        List<ExaminationType> examinationTypes = arguments.size() > 9 ? InputParser.parseExaminationTypeList(arguments.get(9)) : new ArrayList<>();

        UUID doctorUUID = createDoctor.execute(firstName, lastName, street, hoseNumber, zipCode, city, birthDate, phoneNumber, email, examinationTypes);
        return "The doctor was created successfully. The ID of the doctor is: " + doctorUUID.toString();
    }
}
