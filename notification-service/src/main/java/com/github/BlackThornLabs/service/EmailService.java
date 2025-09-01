package com.github.BlackThornLabs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendWelcomeEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Добро пожаловать!");
        message.setText("Здравствуйте! Ваш аккаунт на сайте ваш сайт был успешно создан.");

        mailSender.send(message);
    }

    public void sendGoodbyeEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ваш аккаунт удален");
        message.setText("Здравствуйте! Ваш аккаунт был удалён.");

        mailSender.send(message);
    }
}
