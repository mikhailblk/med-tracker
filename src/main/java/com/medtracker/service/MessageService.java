package com.medtracker.service;

import com.medtracker.entity.Message;
import com.medtracker.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        message.setGelesen(false);
        return messageRepository.save(message);
    }

    public void markAsRead(Long id) {
        messageRepository.findById(id).ifPresent(m -> {
            m.setGelesen(true);
            messageRepository.save(m);
        });
    }
}