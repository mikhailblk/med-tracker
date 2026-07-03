package com.medtracker.controller;

import com.medtracker.entity.Message;
import com.medtracker.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;
    Logger logger = LoggerFactory.getLogger(MessageController.class);


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        logger.info("Getting all messages");
        return messageService.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        logger.info("Sending a message to a message");
        return ResponseEntity.ok(messageService.sendMessage(message));
    }

    @PutMapping("/{id}/gelesen")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        messageService.markAsRead(id);
        logger.info("Marking as read");
        return ResponseEntity.ok().build();
    }
}