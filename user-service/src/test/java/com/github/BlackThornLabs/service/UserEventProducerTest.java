package com.github.BlackThornLabs.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserEventProducerTest {

    @Autowired
    private UserEventProducer userEventProducer;

    @Test
    void testSendUserEvent() {
        assertThat(userEventProducer).isNotNull();
    }
}
