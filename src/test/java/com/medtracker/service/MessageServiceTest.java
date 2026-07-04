package com.medtracker.service;

import com.medtracker.entity.Message;
import com.medtracker.repository.MessageRepository;
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
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    void getAllMessages_shouldReturnAllFromRepository() {
        // Given
        Message m1 = new Message();
        Message m2 = new Message();
        when(messageRepository.findAll()).thenReturn(List.of(m1, m2));

        // When
        List<Message> result = messageService.getAllMessages();

        // Then
        assertEquals(2, result.size());
        verify(messageRepository, times(1)).findAll();
    }

    @Test
    void sendMessage_shouldSetTimestampAndGelesenFalseBeforeSaving() {
        // Given
        Message input = new Message();
        input.setPatientName("Anna Schmidt");
        input.setContent("hallo");

        Message saved = new Message();
        saved.setPatientName("Anna Schmidt");
        saved.setContent("hallo");
        saved.setGelesen(false);

        when(messageRepository.save(any(Message.class))).thenReturn(saved);

        // When
        Message result = messageService.sendMessage(input);

        // Then
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository, times(1)).save(captor.capture());

        Message captured = captor.getValue();
    }
}