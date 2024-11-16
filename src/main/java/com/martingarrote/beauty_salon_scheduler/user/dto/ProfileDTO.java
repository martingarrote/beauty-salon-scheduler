package com.martingarrote.beauty_salon_scheduler.user.dto;

public record ProfileDTO(

        String name,

        String email,

        String faceShape,

        String hairCurl,

        String favoriteEmployeeName,

        Integer loyaltyPoints,

        String about,

        String instagram
) {
}
