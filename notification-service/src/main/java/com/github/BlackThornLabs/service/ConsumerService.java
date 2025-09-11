package com.github.BlackThornLabs.service;

import com.github.BlackThornLabs.dto.UserEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final EmailService emailService;

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consumeUserEvent(UserEventDTO event) {
        log.info("Received Kafka event: {} for email: {}", event.getEventType(), event.getEmail());
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