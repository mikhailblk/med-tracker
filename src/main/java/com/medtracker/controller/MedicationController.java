package com.medtracker.controller;

import com.medtracker.dto.MedicationDTO;
import com.medtracker.service.MedicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
@CrossOrigin(origins = "*") // Erlaubt Zugriff vom Frontend
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // GET /api/medications
    @GetMapping
    public List<MedicationDTO> getAllMedications() {
        return medicationService.getAllMedications();
    }

    // POST /api/medications
    @PostMapping
    public MedicationDTO addMedication(@RequestBody MedicationDTO medicationDTO) {
        return medicationService.saveMedication(medicationDTO);
    }
}