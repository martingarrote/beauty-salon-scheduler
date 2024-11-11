package com.martingarrote.beauty_salon_scheduler.mapper;

import com.martingarrote.beauty_salon_scheduler.appointment.Appointment;
import com.martingarrote.beauty_salon_scheduler.appointment.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    AppointmentDTO toDTO(Appointment appointment);

}
