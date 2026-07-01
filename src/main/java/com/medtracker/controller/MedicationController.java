package com.medtracker.controller;

import com.medtracker.dto.MedicationDTO;
import com.medtracker.service.MedicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medications")
@CrossOrigin(origins = "*")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    // GET /api/medications?patientName=Anna Schmidt
    @GetMapping
    public List<MedicationDTO> getMedications(
            @RequestParam(required = false) String patientName) {
        if (patientName != null && !patientName.isBlank()) {
            return medicationService.getMedicationsByPatient(patientName);
        }
        return medicationService.getAllMedications();
    }

    // POST /api/medications
    @PostMapping
    public MedicationDTO addMedication(@RequestBody MedicationDTO medicationDTO) {
        return medicationService.saveMedication(medicationDTO);
    }

    // DELETE /api/medications/{id}
    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
}