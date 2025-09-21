#!/bin/bash

# Переходим в директорию скрипта
cd "$(dirname "$0")"

echo "🛑 Остановка микросервисного проекта CRUD"
echo "========================================"

# Остановка сервисов
echo "⏹️ Остановка всех сервисов..."
docker-compose down

# Очистка
echo "🧹 Очистка Docker ресурсов..."
docker system prune -f
docker volume prune -f

echo ""
echo "✅ Все сервисы остановлены и ресурсы очищены!"