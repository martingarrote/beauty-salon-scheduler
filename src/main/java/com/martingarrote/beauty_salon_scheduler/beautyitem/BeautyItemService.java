package com.martingarrote.beauty_salon_scheduler.beautyitem;


import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemPatchDTO;
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
    private final BeautyItemMapper mapper = BeautyItemMapper.INSTANCE;

    public BeautyItemDTO create(BeautyItemDTO dto) {
        BeautyItem entity = mapper.toEntity(dto);

        BeautyItem savedEntity = repository.save(entity);

        return mapper.toDTO(savedEntity);
    }

    public List<BeautyItemDTO> findAll() {
        List<BeautyItem> dtoList = repository.findAll();

        return dtoList.stream().map(mapper::toDTO).toList();
    }

    public BeautyItemDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(RuntimeException::new); // change to custom exception
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

    public BeautyItemDTO patchUpdate(Long id, BeautyItemPatchDTO patchDTO) {
        BeautyItem beautyItem = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found")); // change to custom exception

        if (patchDTO.name() != null) {
            beautyItem.setName(patchDTO.name());
        }
        if (patchDTO.description() != null) {
            beautyItem.setDescription(patchDTO.description());
        }
        if (patchDTO.duration() != null) {
            beautyItem.setDuration(patchDTO.duration());
        }
        if (patchDTO.price() != null) {
            beautyItem.setPrice(patchDTO.price());
        }

        return mapper.toDTO(repository.save(beautyItem));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Beauty item not found with id " + id); // change to custom exception
        }

        repository.deleteById(id);
    }

}
