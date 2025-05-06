package com.br.infrastructure.dto.guest;

import com.br.core.entities.Guest;
import com.br.infrastructure.domain.GuestEntity;

public record GuestEntityToGuest(GuestEntity guestEntity) {

    public Guest toGuest(){
        return new Guest.GuestBuilder()
                .builder()
                .id(guestEntity.getId())
                .name(guestEntity.getName())
                .email(guestEntity.getEmail())
                .document(guestEntity.getDocument())
                .guestStatus(guestEntity.getGuestStatus())
                .eventId(guestEntity.getEventId())
                .registeredAt(guestEntity.getRegisteredAt())
                .build();
    }
}
