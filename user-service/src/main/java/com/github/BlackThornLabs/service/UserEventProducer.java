package com.github.BlackThornLabs.service;

import com.github.BlackThornLabs.dto.UserEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserEventProducer {
    private final KafkaTemplate<String, UserEventDTO> kafkaEventTemplate;
    private static final String TOPIC = "UserEvent";

    public void sendUserEvent(String email, UserEventDTO.EventType eventType) {
        try {
            UserEventDTO event = new UserEventDTO();
            event.setEmail(email);
            event.setEventType(eventType);

            kafkaEventTemplate.send(TOPIC, event);
            log.info("UserEventProducer отправил user event в Kafka: {} для пользователя: {}", event.getEventType(), event.getEmail());
        }  catch (Exception e) {
            log.error("Ошибка при отправке userEvent в Kafka: {}", e.getMessage());
        }

    }

}
