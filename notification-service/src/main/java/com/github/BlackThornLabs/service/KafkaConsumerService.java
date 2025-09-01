package com.github.BlackThornLabs.service;

import com.github.BlackThornLabs.dto.UserEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consumeUserEvent(UserEventDTO event) {
        switch (event.getEventType()) {
            case CREATED:
                emailService.sendWelcomeEmail(event.getEmail());
                break;
            case DELETED:
                emailService.sendGoodbyeEmail(event.getEmail());
                break;
        }
    }
}