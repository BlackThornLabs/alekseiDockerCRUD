#!/bin/bash

cd "$(dirname "$0")"|| exit

echo "Сборка проекта..."
mvn clean package -DskipTests -Dmaven.test.skip=true

if [ $? -ne 0 ]; then
    echo "Ошибка сборки!"
    exit 1
fi

echo "Запуск Docker Compose..."
docker-compose up -d --build

echo "Готово! Сервисы запускаются..."
echo "Eureka: http://localhost:8761"
echo "Gateway: http://localhost:8080"