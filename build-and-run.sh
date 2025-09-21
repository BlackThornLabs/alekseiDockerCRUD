#!/bin/bash

# Сборка всех сервисов
echo "Building all services..."
./mvnw clean package -DskipTests

# Запуск Docker Compose
echo "Starting Docker Compose..."
docker-compose up -d --build

# Ожидание запуска сервисов
echo "Waiting for services to start..."
sleep 30

# Проверка статуса сервисов
echo "Checking services status..."
docker-compose ps
docker-compose logs --tail=50 user-service