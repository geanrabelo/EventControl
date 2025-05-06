package com.br.infrastructure.dto;

import com.br.core.enums.EventType;

import java.time.LocalDateTime;

public record EventCreation(String name,
                            String description,
                            String location,
                            LocalDateTime datetime,
                            Integer capacity,
                            EventType eventType) {

}
