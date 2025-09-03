package com.github.BlackThornLabs.configuration;

import com.github.BlackThornLabs.dto.UserEventDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonSerializer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ser.std.StringSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaTemplate<String, UserEventDTO> kafkaEventTemplate(
            ProducerFactory<String, UserEventDTO> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ProducerFactory<String, UserEventDTO> producerFactory() {
        // Настройка ProducerFactory с сериализаторами для String и UserEventDTO
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Используйте JsonSerializer для DTO
        return new DefaultKafkaProducerFactory<>(configProps);
    }
}
