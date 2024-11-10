package com.martingarrote.beauty_salon_scheduler.appointment;

import com.martingarrote.beauty_salon_scheduler.appointment.dto.AppointmentDTO;
import com.martingarrote.beauty_salon_scheduler.appointment.dto.CreationAppointmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.martingarrote.beauty_salon_scheduler.utils.AuthenticationUtils.getUserId;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(@RequestBody CreationAppointmentDTO dto, Authentication auth) {
        System.out.println(getUserId(auth));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto, getUserId(auth)));
    }

}
