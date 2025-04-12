# Stage 1: Build the app
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app with only the jar
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/credit-simulator.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
