package com.br.infrastructure.dto.guest;

import com.br.core.entities.Guest;

public record GuestCreationToGuest(GuestCreation guestCreation) {

    public Guest toGuest(){
        return new Guest.GuestBuilder()
                .builder()
                .name(guestCreation.name())
                .email(guestCreation.email())
                .document(guestCreation.document())
                .guestStatus(guestCreation.guestStatus())
                .eventId(guestCreation.eventId())
                .build();
    }
}
