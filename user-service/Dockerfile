# Build stage
FROM maven:3.9.11-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:21-jdk-slim
WORKDIR /app
RUN groupadd -r spring && useradd -r -g spring spring
COPY --from=build --chown=spring:spring /app/target/user-service.jar app.jar
USER spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]