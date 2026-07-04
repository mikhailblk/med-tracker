package com.medtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medtracker.entity.Appointment;
import com.medtracker.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllAppointments_shouldReturnList() throws Exception {
        // Given
        Appointment a1 = new Appointment();
        a1.setPatientName("Anna Schmidt");
        a1.setStatus("BESTAETIGT");
        Appointment a2 = new Appointment();
        a2.setPatientName("Max Mustermann");
        a2.setStatus("ANGEFRAGT");

        when(appointmentService.getAllAppointments()).thenReturn(List.of(a1, a2));

        // When & Then
        mockMvc.perform(get("/api/appointments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].patientName").value("Anna Schmidt"))
                .andExpect(jsonPath("$[1].patientName").value("Max Mustermann"));
    }

    @Test
    void createAppointment_shouldSaveAndReturnAppointment() throws Exception {
        // Given
        Appointment input = new Appointment();
        input.setPatientName("Anna Schmidt");
        input.setDate(LocalDate.of(2026, 7, 25));
        input.setTime("11:00");

        Appointment saved = new Appointment();
        saved.setPatientName("Anna Schmidt");
        saved.setDate(LocalDate.of(2026, 7, 25));
        saved.setTime("11:00");
        saved.setStatus("ANGEFRAGT");

        when(appointmentService.createAppointment(any(Appointment.class))).thenReturn(saved);

        // When & Then
        mockMvc.perform(post("/api/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientName").value("Anna Schmidt"))
                .andExpect(jsonPath("$.status").value("ANGEFRAGT"));
    }

    @Test
    void updateStatus_shouldReturnUpdatedAppointment() throws Exception {
        // Given
        Appointment updated = new Appointment();
        updated.setPatientName("Anna Schmidt");
        updated.setStatus("BESTAETIGT");

        when(appointmentService.updateStatus(eq(1L), eq("BESTAETIGT"))).thenReturn(updated);

        // When & Then
        mockMvc.perform(put("/api/appointments/1/status")
                        .param("status", "BESTAETIGT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("BESTAETIGT"));
    }
}