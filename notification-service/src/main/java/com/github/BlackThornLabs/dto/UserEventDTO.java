package com.github.BlackThornLabs.dto;

import lombok.Data;

@Data
public class UserEventDTO {
    private String email;
    private EventType eventType;

    public enum EventType {
        CREATED, DELETED
    }
}
