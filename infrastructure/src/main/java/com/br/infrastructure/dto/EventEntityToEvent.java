package com.br.infrastructure.dto;

import com.br.core.entities.Event;
import com.br.infrastructure.domain.EventEntity;

public record EventEntityToEvent(EventEntity eventEntity) {

    public Event toEvent(){
        return new Event.EventBuilder()
                .builder()
                .id(eventEntity.getId())
                .name(eventEntity.getName())
                .description(eventEntity.getDescription())
                .location(eventEntity.getLocation())
                .dateTime(eventEntity.getDatetime())
                .capacity(eventEntity.getCapacity())
                .eventType(eventEntity.getEventType())
                .createdAt(eventEntity.getCreatedAt())
                .build();
    }
}
