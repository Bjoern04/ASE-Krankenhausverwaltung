import de.dhbw.aggregates.doctor.repository.DoctorRepository;
import de.dhbw.aggregates.examination.repository.ExaminationRepository;
import de.dhbw.aggregates.patient.repository.PatientRepository;
import de.dhbw.aggregates.examination.util.ExaminationWithPatientName;
import de.dhbw.use_cases.ShowDoctorExaminationPlan;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShowDoctorExaminationPlanTest {

    @Test
    void testExecute_callsRepositoriesAndReturnsResult() throws FileNotFoundException {
        // Arrange
        DoctorRepository doctorRepository = mock(DoctorRepository.class);
        ExaminationRepository examinationRepository = mock(ExaminationRepository.class);
        PatientRepository patientRepository = mock(PatientRepository.class);

        UUID doctorId = UUID.randomUUID();

        when(doctorRepository.findDoctorById(doctorId)).thenReturn(Mockito.mock(de.dhbw.aggregates.doctor.entity.Doctor.class));
        when(examinationRepository.loadExaminations()).thenReturn(Collections.emptyList());

        ShowDoctorExaminationPlan useCase = new ShowDoctorExaminationPlan(doctorRepository, examinationRepository, patientRepository);

        // Act
        List<ExaminationWithPatientName> result = useCase.execute(doctorId, false);

        // Assert
        assertNotNull(result);
        verify(doctorRepository, times(1)).findDoctorById(doctorId);
        verify(examinationRepository, times(1)).loadExaminations();
        // patientRepository wird im Beispiel nicht aufgerufen, da keine Examinations vorhanden sind
        verifyNoMoreInteractions(patientRepository);
    }
}