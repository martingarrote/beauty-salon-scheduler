package com.martingarrote.beauty_salon_scheduler.beautyitem;


import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.mapper.BeautyItemMapper;
import com.martingarrote.beauty_salon_scheduler.mapper.common.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PageDTO<BeautyItemDTO> search(String name, int duration, double price, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BeautyItem> result = repository.search(name, duration, price, pageable);

        return new PageDTO<>(
                result.getContent().stream().map(mapper::toDTO).toList(),
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}
