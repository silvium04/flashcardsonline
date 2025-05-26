# -------- STAGE 1: Maven Build --------
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY ../ .
RUN mvn clean package -pl backend -am -DskipTests

# -------- STAGE 2: Slim Runtime --------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/backend/target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]