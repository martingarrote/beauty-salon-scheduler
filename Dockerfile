FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/beauty_salon_scheduler.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
