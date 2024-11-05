package com.martingarrote.beauty_salon_scheduler.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class BeautyItemMapper {
    BeautyItemMapper INSTANCE = Mappers.getMapper(BeautyItemMapper.class);
}
