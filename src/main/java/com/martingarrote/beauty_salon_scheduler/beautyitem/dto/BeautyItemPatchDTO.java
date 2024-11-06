package com.martingarrote.beauty_salon_scheduler.beautyitem.dto;

public record BeautyItemPatchDTO(
        String name,
        String description,
        Integer duration,
        Double price
) {
}
