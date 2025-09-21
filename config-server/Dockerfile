# Билд стадия
FROM eclipse-temurin:17-jdk-alpine AS builder

# Установка Maven
RUN apk add --no-cache maven

WORKDIR /app

# Копирование pom.xml и исходников
COPY pom.xml .
COPY src ./src

# Сборка приложения
RUN mvn clean package -DskipTests

# Финальная стадия
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Копирование JAR файла из стадии builder
COPY --from=builder /app/target/*.jar app.jar

# Добавление healthcheck
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
    CMD curl -f http://localhost:${SERVER_PORT}/actuator/health || exit 1

EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-jar", "/app/app.jar"]