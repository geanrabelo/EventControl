package com.br.infrastructure.redis;

import com.br.infrastructure.domain.CheckinEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record CheckinRedis(UUID id,
                           UUID guestId,
                           UUID eventId,
                           @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                           LocalDateTime checkinTime,
                           String device) {
    public static CheckinRedis toCheckinRedis(CheckinEntity checkinEntity){
        return new CheckinRedis(
                checkinEntity.getId(),
                checkinEntity.getGuestId(),
                checkinEntity.getEventId(),
                checkinEntity.getCheckinTime(),
                checkinEntity.getDevice()
        );
    }
}
