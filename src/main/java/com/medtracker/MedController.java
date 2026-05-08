package com.medtracker;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/med")
public class MedController {

    private final MedicationRepository repo;

    public MedController(MedicationRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/hello")
    public String hello() {
        return "MedTracker is running!";
    }

    @GetMapping("/medications")
    public List<Medication> getAll() {
        return repo.findAll();
    }

    @PostMapping("/medications")
    public Medication create(@RequestBody Medication medication) {
        return repo.save(medication);
    }
}