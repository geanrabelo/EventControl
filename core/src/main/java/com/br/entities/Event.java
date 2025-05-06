package com.br.entities;

import com.br.enums.EventType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Event {

    private UUID id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime datetime;
    private Integer capacity;
    private EventType eventType;
    private LocalDateTime createdAt;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Event(UUID id, String name, String description, String location, LocalDateTime datetime, Integer capacity, EventType eventType, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.datetime = datetime;
        this.capacity = capacity;
        this.eventType = eventType;
        this.createdAt = createdAt;
    }

    public static class EventBuilder{
        private UUID id;
        private String name;
        private String description;
        private String location;
        private LocalDateTime datetime;
        private Integer capacity;
        private EventType eventType;
        private LocalDateTime createdAt;

        public EventBuilder builder(){
            return new EventBuilder();
        }

        public EventBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public EventBuilder name(String name) {
            this.name = name;
            return this;
        }

        public EventBuilder description(String description) {
            this.description = description;
            return this;
        }

        public EventBuilder location(String location) {
            this.location = location;
            return this;
        }

        public EventBuilder dateTime(LocalDateTime datetime) {
            this.datetime = datetime;
            return this;
        }

        public EventBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public EventBuilder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public EventBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Event build(){
            return new Event(
                    this.id,
                    this.name,
                    this.description,
                    this.location,
                    this.datetime,
                    this.capacity,
                    this.eventType,
                    this.createdAt);
        }
    }
}
