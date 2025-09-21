FROM openjdk:17-jdk-slim-bullseye

WORKDIR /app

COPY /target/*.jar app.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "app.jar"]