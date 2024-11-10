package com.martingarrote.beauty_salon_scheduler.appointment.dto;

import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.dto.EmployeeDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public record AppointmentDTO(
        LocalDateTime dateTime,

        EmployeeDTO employee,

        UserDTO customer,

        List<BeautyItemDTO> beautyItems,

        String observations,

        Double totalPrice
) {
}
