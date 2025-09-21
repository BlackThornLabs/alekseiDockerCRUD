# 🚀 User Management System

Микросервисная система управления пользователями с полным стеком Spring Cloud. Реализована на основе современных паттернов микросервисной архитектуры.

## 📦 Архитектура системы

[Пользователь]
│
▼
crud-service/
├── gateway-api/              # API Gateway
├── user-service/             # Сервис пользователей
├── notification-service/     # Сервис уведомлений
├── config-server/           # Config Server
├── service-discovery/       # Eureka Server
└── docker-compose.yml       # Docker конфигурация

## 🛠️ Технологии

- **Java 17** + Spring Boot 3.5.5
- **Spring Cloud** 2023.0.3 (Service Discovery, Config Server, API Gateway)
- **PostgreSQL 17** → Основная база данных
- **Apache Kafka 3.9.0** → Брокер сообщений
- **Docker + Docker Compose** → Контейнеризация
- **Maven** → Сборка и управление зависимостями
- **Lombok** → Уменьшение boilerplate кода
- **Swagger UI** → (интерактивная документация)

## ✅ Требования

- Java 17+ 
- Docker Desktop 4.12+ и Docker Compose
- Maven 3.6+
- (Опционально) IDE с поддержкой Spring Boot

## 🚀 Быстрый запуск

### 1. Клонирование, сборка, запуск
```bash
git clone <your-repo>
cd crud-service
mvn clean package -DskipTests
docker-compose up -d
```
### 2. Проверка статуса
```bash
docker-compose ps
```
### 3. Проверьте регистрацию сервисов в Eureka:
http://localhost:8761
