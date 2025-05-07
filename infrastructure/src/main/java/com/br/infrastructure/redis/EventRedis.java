package com.br.infrastructure.redis;

import com.br.core.enums.EventType;
import com.br.infrastructure.domain.EventEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventRedis(UUID id,
                         String name,
                         String description,
                         String location,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                         LocalDateTime datetime,
                         Integer capacity,
                         EventType eventType,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                         LocalDateTime createdAt) {

    public static EventRedis toEventRedis(EventEntity eventEntity){
        return new EventRedis(
                eventEntity.getId(),
                eventEntity.getName(),
                eventEntity.getDescription(),
                eventEntity.getLocation(),
                eventEntity.getDatetime(),
                eventEntity.getCapacity(),
                eventEntity.getEventType(),
                eventEntity.getCreatedAt()
        );
    }
}
