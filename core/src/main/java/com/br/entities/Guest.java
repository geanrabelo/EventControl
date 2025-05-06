package com.br.entities;

import com.br.enums.GuestStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Guest {

    private UUID id;
    private String name;
    private String email;
    private String document;
    private GuestStatus guestStatus;
    private UUID eventId;
    private LocalDateTime registeredAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public GuestStatus getGuestStatus() {
        return guestStatus;
    }

    public void setGuestStatus(GuestStatus guestStatus) {
        this.guestStatus = guestStatus;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Guest(UUID id, String name, String email, String document, GuestStatus guestStatus, UUID eventId, LocalDateTime registeredAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.guestStatus = guestStatus;
        this.eventId = eventId;
        this.registeredAt = registeredAt;
    }
}
