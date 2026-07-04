package com.medtracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medtracker.entity.Message;
import com.medtracker.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllMessages_shouldReturnList() throws Exception {
        // Given
        Message m1 = new Message();
        m1.setPatientName("Anna Schmidt");
        m1.setContent("hey");
        Message m2 = new Message();
        m2.setPatientName("Anna Schmidt");
        m2.setContent("hello");

        when(messageService.getAllMessages()).thenReturn(List.of(m1, m2));

        // When & Then
        mockMvc.perform(get("/api/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].content").value("hey"))
                .andExpect(jsonPath("$[1].content").value("hello"));
    }

    @Test
    void sendMessage_shouldSaveAndReturnMessage() throws Exception {
        // Given
        Message input = new Message();
        input.setPatientName("Anna Schmidt");
        input.setContent("test");

        Message saved = new Message();
        saved.setPatientName("Anna Schmidt");
        saved.setContent("test");
        saved.setGelesen(false);

        when(messageService.sendMessage(any(Message.class))).thenReturn(saved);

        // When & Then
        mockMvc.perform(post("/api/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("test"))
                .andExpect(jsonPath("$.gelesen").value(false));
    }

    @Test
    void markAsRead_shouldReturnOk() throws Exception {
        // When & Then
        mockMvc.perform(put("/api/messages/5/gelesen"))
                .andExpect(status().isOk());

        verify(messageService).markAsRead(5L);
    }
}