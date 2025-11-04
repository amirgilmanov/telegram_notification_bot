FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
#кешируем зависимости
RUN mvn clean dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim AS runtime
WORKDIR /app

COPY --from=build /app/target/telegram_send_feedback-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]