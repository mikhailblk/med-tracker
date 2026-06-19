package com.medtracker.dto;

public class MedicationDTO {

    private String name;
    private String dosage;
    private String time;

    // Standard-Konstruktor (wichtig für die Umwandlung durch Jackson)
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
}