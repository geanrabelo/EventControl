package com.br.core.exceptions;

public class GuestNotFound extends RuntimeException {
    public GuestNotFound(String message) {
        super(message);
    }
}
