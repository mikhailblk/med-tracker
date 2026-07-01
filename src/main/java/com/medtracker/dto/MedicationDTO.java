package com.medtracker.dto;

public class MedicationDTO {

    private String name;
    private String dosage;
    private String time;
    private String patientName;
    private Long id;

    // Standard-Konstruktor
    public MedicationDTO() {}

    public MedicationDTO(String name, String dosage, String time) {
        this.name = name;
        this.dosage = dosage;
        this.time = time;
    }

    // Getter und Setter
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}