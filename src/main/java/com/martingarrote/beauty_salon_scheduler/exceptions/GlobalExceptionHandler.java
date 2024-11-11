package com.martingarrote.beauty_salon_scheduler.exceptions;

import com.martingarrote.beauty_salon_scheduler.exceptions.appointment.AppointmentNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.appointment.AppointmentOverlapException;
import com.martingarrote.beauty_salon_scheduler.exceptions.beautyitem.BeautyItemNotFoundException;
import com.martingarrote.beauty_salon_scheduler.exceptions.common.UnauthorizedAccessException;
import com.martingarrote.beauty_salon_scheduler.exceptions.user.*;
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

    @ExceptionHandler(BeautyItemNotFoundException.class)
    private ResponseEntity<ExceptionResponse> beautyItemNotFoundExceptionHandler(BeautyItemNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    private ResponseEntity<ExceptionResponse> emailAlreadyInUseExceptionHandler(EmailAlreadyInUseException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    private ResponseEntity<ExceptionResponse> emailNotFoundExceptionHandler(EmailNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    private ResponseEntity<ExceptionResponse> wrongPasswordExceptionHandler(WrongPasswordException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<ExceptionResponse> userNotFoundExceptionHandler(UserNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(UserIdentificationException.class)
    private ResponseEntity<ExceptionResponse> userIdentificationExceptionHandler(UserIdentificationException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    private ResponseEntity<ExceptionResponse> unauthorizedAccessExceptionHandler(UnauthorizedAccessException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(AppointmentOverlapException.class)
    private ResponseEntity<ExceptionResponse> appointmentOverlapExceptionHandler(AppointmentOverlapException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.CONFLICT, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    private ResponseEntity<ExceptionResponse> appointmentNotFoundExceptionHandler(AppointmentNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
