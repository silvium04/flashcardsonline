# Nutzt Java 21 (Temurin ist eine offizielle, stabile OpenJDK-Distribution)
FROM eclipse-temurin:21-jdk-alpine

# Setze Arbeitsverzeichnis
WORKDIR /app

# Kopiere die gebaute .jar-Datei ins Image
COPY target/*.jar app.jar

# Start der Spring Boot App
ENTRYPOINT ["java", "-jar", "app.jar"]