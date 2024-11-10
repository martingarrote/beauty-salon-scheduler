package com.martingarrote.beauty_salon_scheduler.appointment.dto;

import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.user.User;

import java.time.LocalDateTime;
import java.util.List;

public record AppointmentDTO(
        LocalDateTime dateTime,

        User employee,

        User customer,

        List<BeautyItem> beautyItems,

        String observations,

        Double totalPrice
) {
}
