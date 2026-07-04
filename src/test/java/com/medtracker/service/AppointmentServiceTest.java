package com.medtracker.service;

import com.medtracker.entity.Appointment;
import com.medtracker.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void getAllAppointments_shouldReturnAllFromRepository() {
        // Given
        Appointment a1 = new Appointment();
        Appointment a2 = new Appointment();
        when(appointmentRepository.findAll()).thenReturn(List.of(a1, a2));

        // When
        List<Appointment> result = appointmentService.getAllAppointments();

        // Then
        assertEquals(2, result.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void createAppointment_shouldSetStatusToAngefragtBeforeSaving() {
        // Given
        Appointment input = new Appointment();
        input.setPatientName("Anna Schmidt");

        Appointment saved = new Appointment();
        saved.setPatientName("Anna Schmidt");
        saved.setStatus("ANGEFRAGT");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(saved);

        // When
        Appointment result = appointmentService.createAppointment(input);

        // Then
        ArgumentCaptor<Appointment> captor = ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository, times(1)).save(captor.capture());

        assertEquals("ANGEFRAGT", captor.getValue().getStatus());
        assertEquals("ANGEFRAGT", result.getStatus());
        assertEquals("Anna Schmidt", result.getPatientName());
    }

    @Test
    void updateStatus_shouldUpdateAndSave_whenAppointmentExists() {
        // Given
        Appointment existing = new Appointment();
        existing.setStatus("ANGEFRAGT");

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(existing);

        // When
        Appointment result = appointmentService.updateStatus(1L, "BESTAETIGT");

        // Then
        assertEquals("BESTAETIGT", result.getStatus());
        verify(appointmentRepository, times(1)).save(existing);
    }

    @Test
    void updateStatus_shouldThrowException_whenAppointmentNotFound() {
        // Given
        when(appointmentRepository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> appointmentService.updateStatus(99L, "BESTAETIGT"));

        assertEquals("Termin nicht gefunden", exception.getMessage());
        verify(appointmentRepository, never()).save(any());
    }
}
