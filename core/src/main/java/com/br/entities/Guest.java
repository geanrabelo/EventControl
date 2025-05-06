package com.br.entities;

import com.br.enums.GuestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Guest {

    private UUID id;
    private String name;
    private String email;
    private String document;
    private GuestStatus guestStatus;
    private UUID eventId;
    private LocalDateTime registeredAt;
}
