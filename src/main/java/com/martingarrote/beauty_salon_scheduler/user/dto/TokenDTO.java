package com.martingarrote.beauty_salon_scheduler.user.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDTO(
        @NotBlank
        String token
) {
}
