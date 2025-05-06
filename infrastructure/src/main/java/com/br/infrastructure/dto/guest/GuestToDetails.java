package com.br.infrastructure.dto.guest;

import com.br.core.entities.Guest;

public record GuestToDetails(Guest guest) {

    public GuestDetails toDetails() {
        return new GuestDetails(
                guest.getId(),
                guest.getName(),
                guest.getEmail(),
                guest.getDocument(),
                guest.getGuestStatus(),
                guest.getEventId(),
                guest.getRegisteredAt()
        );
    }
}
