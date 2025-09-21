FROM openjdk:25-ea-17-jdk-slim

WORKDIR /app

COPY ./target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]