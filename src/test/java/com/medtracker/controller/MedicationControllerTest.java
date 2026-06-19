package com.medtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medtracker.dto.MedicationDTO;
import com.medtracker.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MedicationController.class)
class MedicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMedications_shouldReturnList() throws Exception {
        // Given
        MedicationDTO dto1 = new MedicationDTO("Med1", "10mg", "08:00");
        MedicationDTO dto2 = new MedicationDTO("Med2", "20mg", "20:00");
        when(service.getAllMedications()).thenReturn(List.of(dto1, dto2));

        // When & Then
        mockMvc.perform(get("/api/medications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Med1"))
                .andExpect(jsonPath("$[1].name").value("Med2"));
    }

    @Test
    void addMedication_shouldSaveAndReturnDTO() throws Exception {
        // Given
        MedicationDTO dto = new MedicationDTO("NewMed", "50mg", "12:00");
        MedicationDTO savedDto = new MedicationDTO("NewMed", "50mg", "12:00");
        when(service.saveMedication(any(MedicationDTO.class))).thenReturn(savedDto);

        // When & Then
        mockMvc.perform(post("/api/medications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("NewMed"))
                .andExpect(jsonPath("$.dosage").value("50mg"))
                .andExpect(jsonPath("$.time").value("12:00"));
    }
}