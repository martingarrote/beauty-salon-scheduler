package com.martingarrote.beauty_salon_scheduler.exceptions;

import com.martingarrote.beauty_salon_scheduler.exceptions.beautyitem.BeautyItemNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ExceptionResponse> runtimeExceptionHandler(RuntimeException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
