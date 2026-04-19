package com.medtracker;

import org.springframework.web.bind.annotation.*;

@RestController
public class MedController {

    @GetMapping("/hello")
    public String hello() {
        return "MedTracker is running!";
    }

    @GetMapping("/medication/sample")
    public MedicationSample sampleMedication() {
        return new MedicationSample("Ibuprofen", "400mg", "Max Mustermann");
    }

    // Eine einfache Datenklasse ohne Datenbank
    static class MedicationSample {
        private final String name;
        private final String dosage;
        private final String patientName;

        public MedicationSample(String name, String dosage, String patientName) {
            this.name = name;
            this.dosage = dosage;
            this.patientName = patientName;
        }

        public String getName() { return name; }
        public String getDosage() { return dosage; }
        public String getPatientName() { return patientName; }
    }
}