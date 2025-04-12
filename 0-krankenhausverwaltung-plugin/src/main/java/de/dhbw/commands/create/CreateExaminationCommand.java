package de.dhbw.commands.create;

import de.dhbw.commands.Command;
import de.dhbw.use_cases.create.CreateExamination;
import de.dhbw.InputParser;
import de.dhbw.commands.exceptions.WrongAmoutOfParameters;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.storage.DoctorStorage;
import de.dhbw.storage.ExaminationStorage;
import de.dhbw.storage.PatientStorage;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CreateExaminationCommand implements Command {
    private final String argument;

    public CreateExaminationCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException {
        CreateExamination createExamination = new CreateExamination(new ExaminationStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\examinations.json"), new PatientStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\patients.json"), new DoctorStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\doctors.json"));
        List<Object> arguments = InputParser.parseArguments(argument);

        if (arguments.size() != 5) {
            throw new WrongAmoutOfParameters("The wrong amount of parameters. There have to be five parameters but " + arguments.size() + "were entered.");
        }

        // Check examination type
        ExaminationType examinationType;
        try {
            examinationType = ExaminationType.valueOf(arguments.get(0).toString());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Examination type is invalid. Allow values are: " + String.join(", ", ExaminationType.getAllExaminationTypes()));
        }

        // Check start time
        LocalDateTime startTime = InputParser.parseLocalDateTime(arguments.get(1).toString());;

        // Check end time
        LocalDateTime endTime = InputParser.parseLocalDateTime(arguments.get(2).toString());

        // Check patient ID
        UUID patientId;
        try {
            patientId = UUID.fromString(arguments.get(3).toString());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Patient ID is invalid. Please use a valid UUID.");
        }

        // Check doctor ID
        UUID doctorId;
        try {
            doctorId = UUID.fromString(arguments.get(4).toString());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Doctor ID is invalid. Please use a valid UUID.");
        }

        UUID examinationId = createExamination.execute(examinationType, startTime, endTime, patientId, doctorId);

        return "The examination was successfully created. The ID of the examination is: " + examinationId.toString();
    }
}
