package com.github.BlackThornLabs.service;

import com.github.BlackThornLabs.dto.UserEventDTO;
import com.github.BlackThornLabs.dto.UserRequestDTO;
import com.github.BlackThornLabs.dto.UserResponseDTO;
import com.github.BlackThornLabs.mapper.UserMapper;
import com.github.BlackThornLabs.model.User;
import com.github.BlackThornLabs.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    // переменная для продюсера сообщений
    private final UserEventProducer userEventProducer;

    // Создание записи пользователя через DTO
    public UserResponseDTO createUser(@Valid UserRequestDTO request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        // Делегирование отправки события продюсеру
        userEventProducer.sendUserEvent(savedUser.getEmail(), UserEventDTO.EventType.CREATED);
        return userMapper.toResponseDTO(savedUser);
    }

    // Чтение записей из базы
    public Optional<UserResponseDTO> getUserById(long id) {
        return userRepository.findById(id).map(userMapper::toResponseDTO);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserResponseDTO> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).map(userMapper::toResponseDTO);
    }

    // Обновление записи пользователя
    public Optional<UserResponseDTO> updateUser(Long id, UserRequestDTO request) {
        return userRepository.findById(id)
                .map(user -> {
                    request.applyToUser(user); // Используем безопасный метод
                    User updatedUser = userRepository.save(user);
                    return userMapper.toResponseDTO(updatedUser);
                });
    }

    // Удаление записи
    public boolean deleteUser(long id) {
        return userRepository.findById(id)
                .map(user -> {
                    String userEmail = user.getEmail();
                    userRepository.delete(user);
                    // Делегируем отправку события продюсеру
                    userEventProducer.sendUserEvent(userEmail, UserEventDTO.EventType.DELETED);
                    return true;
                })
                .orElse(false);
    }
}
