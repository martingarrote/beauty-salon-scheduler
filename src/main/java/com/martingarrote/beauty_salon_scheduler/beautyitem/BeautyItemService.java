package com.martingarrote.beauty_salon_scheduler.beautyitem;


import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.mapper.BeautyItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BeautyItemService {

    private final BeautyItemRepository repository;
    private final BeautyItemMapper mapper;

    public BeautyItemDTO create(BeautyItemDTO dto) {
        BeautyItem entity = mapper.toEntity(dto);

        BeautyItem savedEntity = repository.save(entity);

        return mapper.toDTO(savedEntity);
    }

    public List<BeautyItemDTO> findAll() {
        List<BeautyItem> dtoList = repository.findAll();

        return dtoList.stream().map(mapper::toDTO).toList();
    }
}
