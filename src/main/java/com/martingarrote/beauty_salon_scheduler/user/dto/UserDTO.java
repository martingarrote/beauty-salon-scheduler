package com.martingarrote.beauty_salon_scheduler.user.dto;

import com.martingarrote.beauty_salon_scheduler.user.enums.FaceShape;
import com.martingarrote.beauty_salon_scheduler.user.enums.HairCurl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(

        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotNull
        FaceShape faceShape,

        @NotNull
        HairCurl hairCurl

) {
}
