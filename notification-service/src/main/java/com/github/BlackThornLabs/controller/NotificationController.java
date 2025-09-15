package com.github.BlackThornLabs.controller;

import com.github.BlackThornLabs.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Уведомления", description = "API для управления email уведомлениями и рассылками")
public class NotificationController {

    private final EmailService emailService;

    @Operation(summary = "Отправить приветственное письмо", description = "Отправляет автоматическое приветственное email сообщение новому пользователю")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Приветственное письмо успешно отправлено"),
            @ApiResponse(responseCode = "400", description = "Неверный формат email адреса"),
            @ApiResponse(responseCode = "500", description = "Ошибка при отправке email")
    })
    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcomeEmail(
            @Parameter(
                    description = "Email адрес получателя",
                    required = true,
                    example = "user@example.com",
                    schema = @Schema(type = "string", format = "email")
            )
            @RequestParam String email
    ) {
        emailService.sendWelcomeEmail(email);
        return ResponseEntity.ok("Welcome email sent to " + email);
    }

    @Operation(summary = "Отправить прощальное письмо",description = "Отправляет прощальное email сообщение при удалении аккаунта пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Прощальное письмо успешно отправлено"),
            @ApiResponse(responseCode = "400", description = "Неверный формат email адреса"),
            @ApiResponse(responseCode = "500", description = "Ошибка при отправке email")
    })
    @PostMapping("/goodbye")
    public ResponseEntity<String> sendGoodbyeEmail(
            @Parameter(
                    description = "Email адрес получателя",
                    required = true,
                    example = "user@example.com",
                    schema = @Schema(type = "string", format = "email")
            )
            @RequestParam String email
    ) {
        emailService.sendGoodbyeEmail(email);
        return ResponseEntity.ok("Goodbye email sent to " + email);
    }
}