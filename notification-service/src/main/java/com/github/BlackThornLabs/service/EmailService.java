package com.github.BlackThornLabs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendWelcomeEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Добро пожаловать!");
        message.setText("Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");
        try {
            mailSender.send(message);
            log.info("Приветственное сообщение успешно отправлено на: {}", email); // ← Лог об успехе
        } catch (Exception e) {
            log.error("¡Ay, caramba! Failed to send welcome email to: {}. Error: {}", email, e.getMessage()); // ← Лог об ошибке
            throw e;
        }
    }

    public void sendGoodbyeEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ваш аккаунт удален");
        message.setText("Здравствуйте! Ваш аккаунт был удалён.");
        try {
            mailSender.send(message);
            log.info("Сообщение об удалении аккаунта успешно отправлено на: {}", email); // ← Лог об успехе
        } catch (Exception e) {
            log.error("¡Ay, caramba! Failed to send goodbye email to: {}. Error: {}", email, e.getMessage()); // ← Лог об ошибке
            throw e;
        }
    }
}
