package com.medtracker.service;

import com.medtracker.dto.MedicationDTO;
import com.medtracker.entity.Medication;
import com.medtracker.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {

    @Mock
    private MedicationRepository repository;

    @InjectMocks
    private MedicationService service;

    @Test
    void saveMedication_shouldSaveAndReturnDTO() {
        // Given
        MedicationDTO dto = new MedicationDTO("TestMed", "100mg", "08:00");
        Medication entity = new Medication("TestMed", "100mg", "08:00");
        Medication savedEntity = new Medication("TestMed", "100mg", "08:00");
        savedEntity.setId(1L);

        when(repository.save(any(Medication.class))).thenReturn(savedEntity);

        // When
        MedicationDTO result = service.saveMedication(dto);

        // Then
        assertNotNull(result);
        assertEquals("TestMed", result.getName());
        assertEquals("100mg", result.getDosage());
        assertEquals("08:00", result.getTime());
        verify(repository, times(1)).save(any(Medication.class));
    }

    @Test
    void getAllMedications_shouldReturnListOfDTOs() {
        // Given
        Medication med1 = new Medication("Med1", "10mg", "08:00");
        med1.setId(1L);
        Medication med2 = new Medication("Med2", "20mg", "20:00");
        med2.setId(2L);
        List<Medication> entities = List.of(med1, med2);

        when(repository.findAll()).thenReturn(entities);

        // When
        List<MedicationDTO> result = service.getAllMedications();

        // Then
        assertEquals(2, result.size());
        assertEquals("Med1", result.get(0).getName());
        assertEquals("Med2", result.get(1).getName());
        verify(repository, times(1)).findAll();
    }
    @Test
    void getMedicationsByPatient_shouldReturnOnlyMatchingPatient() {
        // Given
        Medication med1 = new Medication("Med1", "10mg", "08:00");
        med1.setPatientName("Anna Schmidt");
        Medication med2 = new Medication("Med2", "20mg", "20:00");
        med2.setPatientName("Max Mustermann");

        when(repository.findAll()).thenReturn(List.of(med1, med2));

        // When
        List<MedicationDTO> result = service.getMedicationsByPatient("Anna Schmidt");

        // Then
        assertEquals(1, result.size());
        assertEquals("Med1", result.get(0).getName());
        assertEquals("Anna Schmidt", result.get(0).getPatientName());
    }

    @Test
    void deleteMedication_shouldCallRepositoryDeleteById() {
        // When
        service.deleteMedication(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }
}