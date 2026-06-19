package com.medtracker.service;

import com.medtracker.dto.MedicationDTO;
import com.medtracker.entity.Medication;
import com.medtracker.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    // Konvertiert ein DTO in eine Entity (für POST)
    private Medication toEntity(MedicationDTO dto) {
        return new Medication(dto.getName(), dto.getDosage(), dto.getTime());
    }

    // Konvertiert eine Entity in ein DTO (für GET)
    private MedicationDTO toDto(Medication entity) {
        return new MedicationDTO(entity.getName(), entity.getDosage(), entity.getTime());
    }

    // Neue Medikation speichern (POST)
    public MedicationDTO saveMedication(MedicationDTO dto) {
        Medication entity = toEntity(dto);
        Medication savedEntity = medicationRepository.save(entity);
        return toDto(savedEntity);
    }

    // Alle Medikationen holen (GET)
    public List<MedicationDTO> getAllMedications() {
        return medicationRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}