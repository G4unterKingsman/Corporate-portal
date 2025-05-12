FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Corporate-portal-0.0.1-SNAPSHOT.jar /app/corporate-portal.jar

CMD ["java", "-jar", "corporate-portal.jar"]

EXPOSE 8080