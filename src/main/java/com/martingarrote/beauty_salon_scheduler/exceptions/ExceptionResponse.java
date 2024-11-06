package com.martingarrote.beauty_salon_scheduler.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private HttpStatus status;
    private String message;

}
