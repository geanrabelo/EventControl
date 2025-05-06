package com.br.infrastructure.dto.event;

import com.br.core.entities.Event;

public record EventToDetails(Event event) {

    public EventDetails toEventDetails(){
        return new EventDetails(event.getId(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getDatetime(),
                event.getCapacity(),
                event.getEventType(),
                event.getCreatedAt());
    }
}
