package com.martingarrote.beauty_salon_scheduler.mapper;

import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.dto.EmployeeDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.ProfileDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    ProfileDTO toProfileDTO(User user);
    EmployeeDTO toEmployeeDTO(User user);
}
