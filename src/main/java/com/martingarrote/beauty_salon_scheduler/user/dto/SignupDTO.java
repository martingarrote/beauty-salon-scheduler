package com.martingarrote.beauty_salon_scheduler.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupDTO(

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {
}
