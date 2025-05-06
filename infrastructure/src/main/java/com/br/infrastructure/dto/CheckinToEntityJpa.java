package com.br.infrastructure.dto;

import com.br.core.entities.Checkin;
import com.br.infrastructure.domain.CheckinEntity;

public record CheckinToEntityJpa(Checkin checkin) {

    public CheckinEntity toEntityJpa(){
        return CheckinEntity
                .builder()
                .id(checkin.getId())
                .guestId(checkin.getGuestId())
                .eventId(checkin.getEventId())
                .checkinTime(checkin.getCheckinTime())
                .device(checkin.getDevice())
                .build();
    }
}
