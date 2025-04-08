package de.dhbw;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.dhbw.aggregates.assignment.entity.Assignment;
import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.examination.entity.Examination;
import de.dhbw.mixin.entities.assignment.AssignmentBuilderMixin;
import de.dhbw.mixin.entities.assignment.AssignmentMixin;
import de.dhbw.mixin.entities.doctor.DoctorBuilderMixin;
import de.dhbw.mixin.entities.doctor.DoctorMixin;
import de.dhbw.mixin.entities.examination.ExaminationMixin;
import de.dhbw.mixin.entities.patient.PatientBuilderMixin;
import de.dhbw.mixin.entities.patient.PatientMixin;
import de.dhbw.mixin.entities.room.RoomBuilderMixin;
import de.dhbw.mixin.entities.room.RoomMixin;
import de.dhbw.mixin.value_objects.AddressMixin;
import de.dhbw.mixin.value_objects.ContactMixin;
import de.dhbw.mixin.value_objects.NameMixin;
import de.dhbw.mixin.value_objects.RoomAddressMixin;
import de.dhbw.aggregates.patient.entity.Patient;
import de.dhbw.aggregates.room.entity.Room;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;
import de.dhbw.aggregates.room.value_objects.RoomAddress;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonSerializer {
    private final ObjectMapper objectMapper;

    public JsonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule()); // Unterstützt LocalDate
        addMixins();
        this.objectMapper.findAndRegisterModules();
    }

    private void addMixins() {
        this.objectMapper.addMixIn(Patient.class, PatientMixin.class);
        this.objectMapper.addMixIn(Patient.PatientBuilder.class, PatientBuilderMixin.class);
        this.objectMapper.addMixIn(Room.class, RoomMixin.class);
        this.objectMapper.addMixIn(Room.RoomBuilder.class, RoomBuilderMixin.class);
        this.objectMapper.addMixIn(Doctor.class, DoctorMixin.class);
        this.objectMapper.addMixIn(Doctor.DoctorBuilder.class, DoctorBuilderMixin.class);
        this.objectMapper.addMixIn(Assignment.class, AssignmentMixin.class);
        this.objectMapper.addMixIn(Assignment.AssignmentBuilder.class, AssignmentBuilderMixin.class);
        this.objectMapper.addMixIn(Examination.class, ExaminationMixin.class);

        // Value Objects
        this.objectMapper.addMixIn(Name.class, NameMixin.class);
        this.objectMapper.addMixIn(Address.class, AddressMixin.class);
        this.objectMapper.addMixIn(Contact.class, ContactMixin.class);
        this.objectMapper.addMixIn(RoomAddress.class, RoomAddressMixin.class);

        this.objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    }

    public <T> void serializeUpdateFile(List<T> objects, String filePath) {
        try {
            // Versuche die existierende JSON-Datei zu laden
            List<T> existingObjects = deserialize(filePath, (Class<T>) objects.get(0).getClass());

            // Füge neue Objekte hinzu
            existingObjects.addAll(objects);

            // Schreibe die aktualisierte Liste zurück in die Datei
            objectMapper.writeValue(new File(filePath), existingObjects);
        } catch (IOException e) {
            // Wenn die Datei leer ist oder nicht existiert, schreibe einfach die neuen Objekte
            try {
                objectMapper.writeValue(new File(filePath), objects);
            } catch (IOException ioException) {
                throw new RuntimeException("Fehler beim Speichern der JSON-Datei: " + filePath, ioException);
            }
        }
    }

    public <T> void serializeOverwrite(List<T> objects, String filePath) {
        try {
            objectMapper.writeValue(new File(filePath), objects);
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Überschreiben der JSON-Datei: " + filePath, e);
        }
    }


    public <T> List<T> deserialize(String filePath, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            System.out.println("message" + e.getMessage());
            throw new RuntimeException("Fehler beim Laden der JSON-Datei: " + filePath, e);
        }
    }
}
