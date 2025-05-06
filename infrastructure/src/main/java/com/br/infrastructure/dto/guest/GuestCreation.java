package com.br.infrastructure.dto.guest;

import com.br.core.enums.GuestStatus;

import java.util.UUID;

public record GuestCreation(String name,
                            String email,
                            String document,
                            GuestStatus guestStatus,
                            UUID eventId) {
}
