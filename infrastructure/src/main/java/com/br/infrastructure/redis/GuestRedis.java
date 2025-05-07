package com.br.infrastructure.redis;

import com.br.core.enums.GuestStatus;
import com.br.infrastructure.domain.EventEntity;
import com.br.infrastructure.domain.GuestEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record GuestRedis(UUID id,
                         String name,
                         String email,
                         String document,
                         GuestStatus guestStatus,
                         UUID eventId,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                         LocalDateTime registeredAt) {
    public static GuestRedis toGuestRedis(GuestEntity guestEntity){
        return new GuestRedis(
                guestEntity.getId(),
                guestEntity.getName(),
                guestEntity.getEmail(),
                guestEntity.getDocument(),
                guestEntity.getGuestStatus(),
                guestEntity.getEventId(),
                guestEntity.getRegisteredAt()
        );
    }

}
