package com.br.infrastructure.ex;

import com.br.core.exceptions.EventNotFound;
import com.br.core.exceptions.GuestAlreadyRegisteredEvent;
import com.br.core.exceptions.GuestNotFound;
import com.br.infrastructure.ex.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse eventNotFoundHandler(String message){
        return ErrorResponse.notFound(message);
    }

    @ExceptionHandler(GuestNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse guestNotFoundHandler(String message){
        return ErrorResponse.notFound(message);
    }

    @ExceptionHandler(GuestAlreadyRegisteredEvent.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse guestAlreadyRegisteredEventHandler(String message) {
        return ErrorResponse.conflict(message);
    }
}
