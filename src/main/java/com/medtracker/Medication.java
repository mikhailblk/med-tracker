package com.medtracker;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private LocalDateTime nextDose;
    private String patientName;

    public Medication() {}

    public Medication(String name, String dosage, LocalDateTime nextDose, String patientName) {
        this.name = name;
        this.dosage = dosage;
        this.nextDose = nextDose;
        this.patientName = patientName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public LocalDateTime getNextDose() {
        return nextDose;
    }

    public void setNextDose(LocalDateTime nextDose) {
        this.nextDose = nextDose;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}