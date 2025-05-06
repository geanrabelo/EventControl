package com.br.core.exceptions;

public class GuestAlreadyRegisteredEvent extends RuntimeException {
    public GuestAlreadyRegisteredEvent(String message) {
        super(message);
    }
}
