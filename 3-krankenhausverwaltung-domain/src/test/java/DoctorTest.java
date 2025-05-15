

import de.dhbw.aggregates.doctor.entity.Doctor;
import de.dhbw.aggregates.examination.value_objects.ExaminationType;
import de.dhbw.shared.value_objects.Address;
import de.dhbw.shared.value_objects.Contact;
import de.dhbw.shared.value_objects.Name;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorTest {

    // A - Arrange: Vorbereiten der Testdaten
    private final UUID doctorId = UUID.randomUUID();
    private final Name doctorName = new Name("Max", "Mustermann");
    private final Address doctorAddress = new Address("Musterstraße", "1", "12345", "Musterstadt");
    private final LocalDate doctorBirthDate = LocalDate.of(1980, 1, 1);
    private final Contact doctorContact = new Contact("0123456789", "max.mustermann@example.com");

    @Test
    void testDoctorCreation_ValidData_ShouldCreateDoctor() {
        // A - Arrange
        Doctor.DoctorBuilder builder = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact);

        // A - Act
        Doctor doctor = builder.build();

        // A - Assert
        assertNotNull(doctor);
        assertEquals(doctorId, doctor.getId());
        assertEquals(doctorName, doctor.getName());
        assertEquals(doctorAddress, doctor.getAddress());
        assertEquals(doctorBirthDate, doctor.getDateOfBirth());
        assertEquals(doctorContact, doctor.getContact());
        assertTrue(doctor.getExaminationIds().isEmpty());
        assertTrue(doctor.getExaminationTypes().isEmpty());
    }

    @Test
    void testUpdateName_ValidName_ShouldUpdateDoctorName() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        Name newName = new Name("Erika", "Musterfrau");

        // A - Act
        doctor.updateName(newName);

        // A - Assert
        assertEquals(newName, doctor.getName());
    }

    @Test
    void testAddExamination_NewExaminationId_ShouldAddIdToList() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        UUID examinationId = UUID.randomUUID();

        // A - Act
        doctor.addExamination(examinationId);

        // A - Assert
        assertTrue(doctor.getExaminationIds().contains(examinationId));
        assertEquals(1, doctor.getExaminationIds().size());
    }

    @Test
    void testRemoveExamination_ExistingExaminationId_ShouldRemoveIdFromList() {
        // A - Arrange
        UUID examinationIdToRemove = UUID.randomUUID();
        List<UUID> initialExaminations = List.of(UUID.randomUUID(), examinationIdToRemove, UUID.randomUUID());
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact)
                .withExaminations(initialExaminations)
                .build();

        // A - Act
        doctor.removeExamination(examinationIdToRemove);

        // A - Assert
        assertFalse(doctor.getExaminationIds().contains(examinationIdToRemove));
        assertEquals(2, doctor.getExaminationIds().size());
    }

    @Test
    void testAddExaminationType_NewExaminationType_ShouldAddTypeToList() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        ExaminationType examinationType = ExaminationType.GeneralExamination;

        // A - Act
        doctor.addExaminationType(examinationType);

        // A - Assert
        assertTrue(doctor.getExaminationTypes().contains(examinationType));
        assertEquals(1, doctor.getExaminationTypes().size());
    }

    @Test
    void testUpdateContact_NullContact_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.updateContact(null));
    }

    @Test
    void testAddExamination_NullExaminationId_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        UUID nullExaminationId = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.addExamination(nullExaminationId));
    }

    @Test
    void testDoctorCreation_NullId_ShouldThrowNullPointerException() {
        // A - Arrange
        Name name = new Name("John", "Doe");
        Address address = new Address("Street", "10", "54321", "City");
        Contact contact = new Contact("123456", "john.doe@example.com");

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> new Doctor.DoctorBuilder(null, name, address, LocalDate.now(), contact).build());
    }

    @Test
    void testDoctorCreation_NullName_ShouldThrowNullPointerException() {
        // A - Arrange
        UUID id = UUID.randomUUID();
        Address address = new Address("Street", "10", "54321", "City");
        Contact contact = new Contact("123456", "john.doe@example.com");

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> new Doctor.DoctorBuilder(id, null, address, LocalDate.now(), contact).build());
    }

    @Test
    void testDoctorCreation_NullAddress_ShouldThrowNullPointerException() {
        // A - Arrange
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Contact contact = new Contact("123456", "john.doe@example.com");

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> new Doctor.DoctorBuilder(id, name, null, LocalDate.now(), contact).build());
    }

    @Test
    void testDoctorCreation_NullContact_ShouldThrowNullPointerException() {
        // A - Arrange
        UUID id = UUID.randomUUID();
        Name name = new Name("John", "Doe");
        Address address = new Address("Street", "10", "54321", "City");

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> new Doctor.DoctorBuilder(id, name, address, LocalDate.now(), null).build());
    }

    @Test
    void testUpdateName_NullName_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        Name nullName = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.updateName(nullName));
    }

    @Test
    void testUpdateAddress_NullAddress_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        Address nullAddress = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.updateAddress(nullAddress));
    }

    @Test
    void testUpdateDateOfBirth_NullDateOfBirth_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        LocalDate nullDateOfBirth = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.updateDateOfBirth(nullDateOfBirth));
    }

    @Test
    void testUpdateContact_ValidContact_ShouldUpdateDoctorContact() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        Contact newContact = new Contact("987654321", "erika.musterfrau@example.com");

        // A - Act
        doctor.updateContact(newContact);

        // A - Assert
        assertEquals(newContact, doctor.getContact());
    }

    @Test
    void testAddExaminationType_NullExaminationType_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        ExaminationType nullExaminationType = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.addExaminationType(nullExaminationType));
    }

    @Test
    void testRemoveExamination_NullExaminationId_ShouldThrowNullPointerException() {
        // A - Arrange
        Doctor doctor = new Doctor.DoctorBuilder(doctorId, doctorName, doctorAddress, doctorBirthDate, doctorContact).build();
        UUID nullExaminationId = null;

        // A - Act & Assert
        assertThrows(NullPointerException.class, () -> doctor.removeExamination(nullExaminationId));
    }
}