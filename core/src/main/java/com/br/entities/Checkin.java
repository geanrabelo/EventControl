package com.br.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Checkin {

    private UUID id;
    private UUID guestId;
    private UUID eventId;
    private LocalDateTime checkinTime;
    private String device;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(LocalDateTime checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public Checkin(UUID id, UUID guestId, UUID eventId, LocalDateTime checkinTime, String device) {
        this.id = id;
        this.guestId = guestId;
        this.eventId = eventId;
        this.checkinTime = checkinTime;
        this.device = device;
    }

    public static class CheckinBuilder{
        private UUID id;
        private UUID guestId;
        private UUID eventId;
        private LocalDateTime checkinTime;
        private String device;

        public CheckinBuilder builder(){
            return new CheckinBuilder();
        }

        public CheckinBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CheckinBuilder guestId(UUID guestId) {
            this.guestId = guestId;
            return this;
        }

        public CheckinBuilder eventId(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public CheckinBuilder checkinTime(LocalDateTime checkinTime) {
            this.checkinTime = checkinTime;
            return this;
        }

        public CheckinBuilder device(String device) {
            this.device = device;
            return this;
        }
        //        private UUID id;
        //        private UUID guestId;
        //        private UUID eventId;
        //        private LocalDateTime checkinTime;
        //        private String device;
        //
        public Checkin build(){
            return new Checkin(
                    this.id,
                    this.guestId,
                    this.eventId,
                    this.checkinTime,
                    this.device
            );
        }
    }
}
