package com.br.infrastructure.dto.event;

import com.br.core.entities.Event;
import com.br.infrastructure.domain.EventEntity;

public record EventToEntityJpa(Event event) {

    public EventEntity toJpa(){
        return EventEntity
                .builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .location(event.getLocation())
                .datetime(event.getDatetime())
                .capacity(event.getCapacity())
                .eventType(event.getEventType())
                .createdAt(event.getCreatedAt())
                .build();
    }
}
