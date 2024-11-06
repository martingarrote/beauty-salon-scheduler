package com.martingarrote.beauty_salon_scheduler.beautyitem;

import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
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
}
