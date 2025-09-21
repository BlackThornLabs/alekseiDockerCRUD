#!/bin/bash

echo "🔍 Тестирование Docker сборки..."
echo "================================"

services=("service-discovery" "config-server" "user-service" "notification-service" "gateway-api")

for service in "${services[@]}"; do
    echo "Testing $service..."

    # Проверяем существование JAR
    if [ ! -f "$service/target/$service-1.0.0-SNAPSHOT.jar" ]; then
        echo "❌ $service: JAR не найден!"
        echo "   Ожидаемый путь: $service/target/$service-1.0.0-SNAPSHOT.jar"
        ls -la "$service/target/" 2>/dev/null || echo "   Папка target не существует"
        continue
    fi

    echo "✅ $service: JAR найден"

    # Пробуем собрать образ
    if docker build -f "$service/Dockerfile" -t "test-$service" . ; then
        echo "✅ $service: Docker build успешен"
    else
        echo "❌ $service: Docker build failed"
    fi
done