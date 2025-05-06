package com.br.infrastructure.dto.event;

import com.br.core.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDetails(UUID id,
                           String name,
                           String description,
                           String location,
                           LocalDateTime dateTime,
                           Integer capacity,
                           EventType eventType,
                           LocalDateTime createdAt) {
}
