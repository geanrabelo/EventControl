package com.br.core.entities;

import com.br.core.enums.GuestStatus;

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

    public static class GuestBuilder{
        private UUID id;
        private String name;
        private String email;
        private String document;
        private GuestStatus guestStatus;
        private UUID eventId;
        private LocalDateTime registeredAt;

        public GuestBuilder builder(){
            return new GuestBuilder();
        }

        public GuestBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public GuestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GuestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public GuestBuilder document(String document) {
            this.document = document;
            return this;
        }

        public GuestBuilder guestStatus(GuestStatus guestStatus) {
            this.guestStatus = guestStatus;
            return this;
        }

        public GuestBuilder eventId(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public GuestBuilder registeredAt(LocalDateTime registeredAt) {
            this.registeredAt = registeredAt;
            return this;
        }

        public Guest build(){
            return new Guest(
                    this.id,
                    this.name,
                    this.email,
                    this.document,
                    this.guestStatus,
                    this.eventId,
                    this.registeredAt
            );
        }
    }
}
