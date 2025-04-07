package de.dhbw.commands;

import de.dhbw.CreateDoctor;
import de.dhbw.commands.exceptions.InvalidParameter;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.storage.DoctorStorage;
import de.dhbw.storage.ExaminationStorage;

import java.time.LocalDate;
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

        if (arguments.size() < 7 || arguments.size() > 9) {
            throw new WrongAmoutOfParameters("Falsche Anzahl an Parameter. Es wurden mindestens neun erwartet, eingegeben wurden aber:" + arguments.size() + "\n" +
                    "Die Parameter sind: Vorname, Nachname, Straße, Hausnummer, PLZ, Stadt, optional ein Geburtsdatum, Telefonnummer und E-Mail.");
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

        UUID doctorUUID = createDoctor.execute(firstName, lastName, street, hoseNumber, zipCode, city, birthDate, phoneNumber, email);
        return "Der Arzt wurde erfolgreich erstellt. Die ID des Arztes ist: " + doctorUUID.toString();
    }
}
