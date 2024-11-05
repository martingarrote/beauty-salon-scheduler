package com.martingarrote.beauty_salon_scheduler.beautyitem;


import com.martingarrote.beauty_salon_scheduler.mapper.BeautyItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BeautyItemService {

    private final BeautyItemRepository repository;
    private final BeautyItemMapper mapper;
}
