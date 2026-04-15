package com.medtracker;

import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
public class MedController {

    @GetMapping("/hello")
    public String hello() {
        return "MedTracker is running!";
    }


    @GetMapping("/medication/sample")
    public Medication sampleMedication() {
        return new Medication(
                "Ibuprofen",
                "400mg",
                LocalDateTime.now().plusHours(8),
                "Max Mustermann"
        );
    }
}