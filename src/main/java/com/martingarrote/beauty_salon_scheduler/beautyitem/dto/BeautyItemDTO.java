package com.martingarrote.beauty_salon_scheduler.beautyitem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BeautyItemDTO(
        @NotNull(message = "ID cannot be null")
        Long id,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Description cannot be blank")
        String description,

        @Positive(message = "Duration must be positive")
        int duration,

        @Positive(message = "Price must be positive")
        double price
) {}
