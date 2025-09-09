package com.github.BlackThornLab.service;

import com.github.BlackThornLabs.dto.UserEventDTO;
import com.github.BlackThornLabs.service.EmailService;
import com.github.BlackThornLabs.service.KafkaConsumerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Testcontainers
public class KafkaConsumerServiceTest {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

    @MockitoBean
    private KafkaTemplate<String, UserEventDTO> kafkaTemplate;

    @MockitoBean
    private KafkaConsumerService kafkaConsumerService;

    @MockitoBean
    private EmailService emailService;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
        registry.add("spring.kafka.consumer.group-id", () -> "test-group");
        registry.add("spring.kafka.consumer.auto-offset-reset", () -> "earliest");
    }

    @Test
    void whenCreateEventReceived_thenWelcomeEmailSent() {
        // Given
        UserEventDTO event = new UserEventDTO();
        event.setEmail("test@example.com");
        event.setEventType(UserEventDTO.EventType.CREATED);

        // When: отправляем сообщение в Kafka
        kafkaTemplate.send("user-events", event);

        // Then: проверяем, что EmailService был вызван
        verify(emailService, timeout(10000).times(1))
                .sendWelcomeEmail("test@example.com");
    }

    @Test
    void whenDeleteEventReceived_thenGoodbyeEmailSent() {
        // Given
        UserEventDTO event = new UserEventDTO();
        event.setEmail("test@example.com");
        event.setEventType(UserEventDTO.EventType.DELETED);

        // When
        kafkaTemplate.send("user-events", event);

        // Then
        verify(emailService, timeout(10000).times(1))
                .sendGoodbyeEmail("test@example.com");
    }
}