package com.martingarrote.beauty_salon_scheduler.beautyitem;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BeautyItemController {

    private final BeautyItemService service;

}
