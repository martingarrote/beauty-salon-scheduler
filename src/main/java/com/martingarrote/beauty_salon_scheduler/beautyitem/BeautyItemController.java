package com.martingarrote.beauty_salon_scheduler.beautyitem;

import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemPatchDTO;
import com.martingarrote.beauty_salon_scheduler.mapper.common.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/beauty-items")
@RequiredArgsConstructor
public class BeautyItemController {

    private final BeautyItemService service;

    @PostMapping
    public ResponseEntity<BeautyItemDTO> create(@RequestBody BeautyItemDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BeautyItemDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeautyItemDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<PageDTO<BeautyItemDTO>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Double price,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(service.search(name, duration, price, page, size));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BeautyItemDTO> patchUpdate(@PathVariable Long id, @RequestBody BeautyItemPatchDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.patchUpdate(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
