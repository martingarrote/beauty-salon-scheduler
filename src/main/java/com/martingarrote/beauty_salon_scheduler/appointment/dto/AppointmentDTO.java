package com.martingarrote.beauty_salon_scheduler.appointment.dto;

import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.EmployeeDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public record AppointmentDTO(

        Long id,

        LocalDateTime startInterval,

        LocalDateTime endInterval,

        EmployeeDTO employee,

        UserDTO customer,

        List<BeautyItemDTO> beautyItems,

        String observations,

        Double totalPrice,

        Boolean active
) {
}
