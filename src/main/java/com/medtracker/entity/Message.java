package com.medtracker.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class Message {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String patientName;
        private String content;
        private LocalDateTime timestamp;
        private boolean gelesen;

        public Message() {}

        public Long getId() { return id; }
        public String getPatientName() { return patientName; }
        public void setPatientName(String patientName) { this.patientName = patientName; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public boolean isGelesen() { return gelesen; }
        public void setGelesen(boolean gelesen) { this.gelesen = gelesen; }
    }

