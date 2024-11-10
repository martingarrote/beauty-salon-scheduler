package com.martingarrote.beauty_salon_scheduler.user.dto;

import java.util.Set;

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
