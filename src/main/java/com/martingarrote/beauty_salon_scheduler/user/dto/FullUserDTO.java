package com.martingarrote.beauty_salon_scheduler.user.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record FullUserDTO(
        String name,

        String email,

        String faceShape,

        String hairCurl,

        String about,

        String instagram,

        Set<String> authorities
) {
}
