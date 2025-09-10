package com.github.BlackThornLabs.controller;

import com.github.BlackThornLabs.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/welcome")
    public String sendWelcomeEmail(@RequestParam String email) {
        emailService.sendWelcomeEmail(email);
        return "Welcome email sent to " + email;
    }

    @PostMapping("/goodbye")
    public String sendGoodbyeEmail(@RequestParam String email) {
        emailService.sendGoodbyeEmail(email);
        return "Goodbye email sent to " + email;
    }
}