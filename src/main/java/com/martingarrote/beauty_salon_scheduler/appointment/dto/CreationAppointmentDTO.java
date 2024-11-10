package com.martingarrote.beauty_salon_scheduler.appointment.dto;

import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.dto.UserDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CreationAppointmentDTO(
        @NotNull
        LocalDateTime dateTime,

        @NotNull
        Long employeeId,

        @NotNull
        List<Long> beautyItemIds,

        String observations
) {
}
