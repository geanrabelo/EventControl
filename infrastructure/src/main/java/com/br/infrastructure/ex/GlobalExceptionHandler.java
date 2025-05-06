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
    public ErrorResponse eventNotFoundHandler(EventNotFound eventNotFound){
        return ErrorResponse.notFound(eventNotFound.getMessage());
    }

    @ExceptionHandler(GuestNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse guestNotFoundHandler(GuestNotFound guestNotFound){
        return ErrorResponse.notFound(guestNotFound.getMessage());
    }

    @ExceptionHandler(GuestAlreadyRegisteredEvent.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse guestAlreadyRegisteredEventHandler(GuestAlreadyRegisteredEvent guestAlreadyRegisteredEvent) {
        return ErrorResponse.conflict(guestAlreadyRegisteredEvent.getMessage());
    }
}
