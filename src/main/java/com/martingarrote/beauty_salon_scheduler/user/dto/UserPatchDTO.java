package com.martingarrote.beauty_salon_scheduler.user.dto;

import com.martingarrote.beauty_salon_scheduler.user.enums.FaceShape;
import com.martingarrote.beauty_salon_scheduler.user.enums.HairCurl;

public record UserPatchDTO(

        String name,

        String email,

        String faceShape,

        String hairCurl,

        String about,

        String instagram
) {
}
