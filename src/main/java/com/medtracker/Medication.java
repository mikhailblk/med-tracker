package com.medtracker;
import jakarta.persistence.*;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String dosage;
        private String patientName;

        public Medication() {}

        public Long getId() { return id; }
        public String getName() { return name; }
        public String getDosage() { return dosage; }
        public String getPatientName() { return patientName; }
        public void setName(String n) { this.name = n; }
        public void setDosage(String d) { this.dosage = d; }
        public void setPatientName(String p) { this.patientName = p; }
    }

