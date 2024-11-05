package com.martingarrote.beauty_salon_scheduler.mapper.common;

import java.util.List;

public record PageDTO<T>(

        List<T> data,
        int page,
        int size,
        long totalElements,
        long totalPages
) {}
