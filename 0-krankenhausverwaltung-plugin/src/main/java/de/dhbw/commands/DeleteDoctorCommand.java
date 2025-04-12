package de.dhbw.commands;

import de.dhbw.DeleteDoctor;
import de.dhbw.storage.DoctorStorage;
import de.dhbw.storage.ExaminationStorage;

import java.io.FileNotFoundException;
import java.util.UUID;

public class DeleteDoctorCommand implements Command {
    private final String argument;

    public DeleteDoctorCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public String execute() throws RuntimeException, FileNotFoundException {
        try {
            UUID doctorUUID = UUID.fromString(argument.trim());
            DeleteDoctor deleteDoctor = new DeleteDoctor(new DoctorStorage(System.getProperty("user.dir") + "/" + "doctors.json"), new ExaminationStorage(System.getProperty("user.dir") + "/" + "examinations.json"));
            return deleteDoctor.execute(doctorUUID);
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The ID is invalid. Please enter a valid UUID.");
        }
    }
}
