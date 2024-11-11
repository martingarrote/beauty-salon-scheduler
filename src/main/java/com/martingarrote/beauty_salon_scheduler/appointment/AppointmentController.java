package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.appointment.dto.AppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.appointment.dto.CreationAppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.martingarrote.beauty_salon_scheduler.utils.AuthenticationUtils.getUserId;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody CreationAppointmentDTO dto, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto, getUserId(auth)));
    }

    @GetMapping("/history")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentHistory(Authentication auth) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAppointmentHistory(getUserId(auth)));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getNextAppointments(Authentication auth) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getNextAppointments(getUserId(auth)));
    }

}
