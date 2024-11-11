package com.martingarrote.beauty_salon_scheduler.appointment.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreationAppointmentDTO(
        @NotNull
        LocalDateTime startInterval,

        @NotNull
        Long employeeId,

        @NotNull
        List<Long> beautyItemIds,

        String observations
) {
}
