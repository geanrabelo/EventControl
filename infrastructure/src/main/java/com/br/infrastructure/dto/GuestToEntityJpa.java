package com.br.infrastructure.dto;

import com.br.core.entities.Guest;
import com.br.infrastructure.domain.GuestEntity;

public record GuestToEntityJpa(Guest guest) {

    public GuestEntity toEntityJpa(){
        return GuestEntity
                .builder()
                .id(guest.getId())
                .name(guest.getName())
                .email(guest.getEmail())
                .document(guest.getDocument())
                .guestStatus(guest.getGuestStatus())
                .eventId(guest.getEventId())
                .registeredAt(guest.getRegisteredAt())
                .build();
    }
}
