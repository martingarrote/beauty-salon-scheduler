package com.martingarrote.beauty_salon_scheduler.mapper;

import com.martingarrote.beauty_salon_scheduler.authority.Authority;
import com.martingarrote.beauty_salon_scheduler.user.User;
import com.martingarrote.beauty_salon_scheduler.user.dto.EmployeeDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.FullUserDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.ProfileDTO;
import com.martingarrote.beauty_salon_scheduler.user.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    ProfileDTO toProfileDTO(User user);
    EmployeeDTO toEmployeeDTO(User user);
    @Mapping(target = "authorities", source = "authorities", qualifiedByName = "authoritiesToStringSet")
    FullUserDTO toFullUserDTO(User user);

    @Named("authoritiesToStringSet")
    default Set<String> authoritiesToStringSet(Set<Authority> authorities) {
        if (authorities == null) {
            return null;
        }

        return authorities.stream()
                .map(Authority::getAuthority)
                .collect(Collectors.toSet());
    }
}
