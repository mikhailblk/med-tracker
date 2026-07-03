package com.medtracker.controller;

import com.medtracker.entity.Appointment;
import com.medtracker.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        logger.info("getAllAppointments called");
        return appointmentService.getAllAppointments();
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        logger.info("Creating a new appointment with id {}", appointment.getId());
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    // Arzt bestätigt oder lehnt ab: status = "BESTAETIGT" oder "ABGELEHNT"
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        logger.info("Updating appointment with id {}", id);
        return ResponseEntity.ok(appointmentService.updateStatus(id, status));
    }
}