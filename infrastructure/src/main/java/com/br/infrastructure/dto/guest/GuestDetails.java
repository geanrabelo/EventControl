package com.br.infrastructure.dto.guest;

import com.br.core.enums.GuestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record GuestDetails(UUID id,
                           String name,
                           String email,
                           String document,
                           GuestStatus guestStatus,
                           UUID eventId,
                           LocalDateTime registeredAt) {
}
