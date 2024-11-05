package com.martingarrote.beauty_salon_scheduler.mapper;

import com.martingarrote.beauty_salon_scheduler.beautyitem.BeautyItem;
import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeautyItemMapper {
    BeautyItemMapper INSTANCE = Mappers.getMapper(BeautyItemMapper.class);

    BeautyItemDTO toDTO(BeautyItem beautyItem);
}
