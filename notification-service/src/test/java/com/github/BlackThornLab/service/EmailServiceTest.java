package com.github.BlackThornLab.service;

import com.github.BlackThornLabs.service.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void sendWelcomeEmail_ShouldSendEmail() {
        String testEmail = "test@example.com";

        emailService.sendWelcomeEmail(testEmail);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendGoodbyeEmail_ShouldSendEmail() {
        String testEmail = "test@example.com";

        emailService.sendGoodbyeEmail(testEmail);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendWelcomeEmail_ShouldContainCorrectContent() {
        String testEmail = "test@example.com";
        String expectedSubject = "Добро пожаловать!";
        String expectedText = "успешно создан";

        emailService.sendWelcomeEmail(testEmail);

        verify(mailSender).send(argThat((SimpleMailMessage message) ->
                message.getTo() != null &&
                        message.getTo()[0].equals(testEmail) &&
                        message.getSubject().contains(expectedSubject) &&
                        message.getText().contains(expectedText)
        ));
    }

    @Test
    void sendGoodbyeEmail_ShouldContainCorrectContent() {
        String testEmail = "test@example.com";
        String expectedSubject = "Ваш аккаунт удален";
        String expectedText = "удалён";

        emailService.sendGoodbyeEmail(testEmail);

        verify(mailSender).send(argThat((SimpleMailMessage message) ->
                {
                    if (message.getTo() == null ||
                            !message.getTo()[0].equals(testEmail)) return false;
                    Assertions.assertNotNull(message.getSubject());
                    if (!message.getSubject().contains(expectedSubject)) return false;
                    Assertions.assertNotNull(message.getText());
                    return message.getText().contains(expectedText);
                }
        ));
    }
}