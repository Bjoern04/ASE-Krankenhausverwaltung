package de.dhbw;

import de.dhbw.patient.entity.Patient;
import de.dhbw.room.entity.Room;
import de.dhbw.shared.RoomAddress;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        RoomAddress roomAddress = new RoomAddress(1, 1, 1);
        RoomAddress roomAddress2 = new RoomAddress(1, 1, 2);
        RoomAddress roomAddress3 = new RoomAddress(1, 1, 3);

        Patient patient1 = new Patient.PatientBuilder(UUID.randomUUID()).build();
        Patient patient2 = new Patient.PatientBuilder(UUID.randomUUID()).build();
        Patient patient3 = new Patient.PatientBuilder(UUID.randomUUID()).build();

        Room room1 = new Room.RoomBuilder(UUID.randomUUID(), roomAddress, 2).build();
        Room room2 = new Room.RoomBuilder(UUID.randomUUID(), roomAddress2, 2).build();
        Room room3 = new Room.RoomBuilder(UUID.randomUUID(), roomAddress3, 2).build();

        AssignRoomToPatientUseCase assignRoomToPatientUseCase = new AssignRoomToPatientUseCase(new PatientStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\patients.json"), new RoomStorage("F:\\Bjoern\\Studium\\AdvancedSoftwareEngineering\\JsonTests\\rooms.json"));
        assignRoomToPatientUseCase.execute(UUID.fromString("19d1271b-22d3-4d9a-b1f5-a8d997e3393e"), UUID.fromString("19d1271b-22d3-4d9a-b1f5-a8d997e3393e"), LocalDate.of(2025, 4, 2), LocalDate.of(2025, 4, 3));

    }
}