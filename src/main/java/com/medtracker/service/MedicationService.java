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

    private Medication toEntity(MedicationDTO dto) {
        Medication m = new Medication(dto.getName(), dto.getDosage(), dto.getTime());
        m.setPatientName(dto.getPatientName());  // ← NEU
        return m;
    }

    private MedicationDTO toDto(Medication entity) {
        MedicationDTO dto = new MedicationDTO(entity.getName(), entity.getDosage(), entity.getTime());
        dto.setPatientName(entity.getPatientName());  // ← NEU
        dto.setId(entity.getId());  // ← NEU (damit Frontend die ID zum Löschen hat)
        return dto;
    }

    public MedicationDTO saveMedication(MedicationDTO dto) {
        Medication entity = toEntity(dto);
        Medication savedEntity = medicationRepository.save(entity);
        return toDto(savedEntity);
    }

    public List<MedicationDTO> getAllMedications() {
        return medicationRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public List<MedicationDTO> getMedicationsByPatient(String patientName) {
        return medicationRepository.findAll()
                .stream()
                .filter(m -> patientName.equals(m.getPatientName()))
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}