package com.martingarrote.beauty_salon_scheduler.beautyitem;

import com.martingarrote.beauty_salon_scheduler.beautyitem.dto.BeautyItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/beauty-items")
@RequiredArgsConstructor
public class BeautyItemController {

    private final BeautyItemService service;

    @PostMapping
    public ResponseEntity<BeautyItemDTO> create(@RequestBody BeautyItemDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(dto));
    }
}
