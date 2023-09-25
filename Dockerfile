FROM openjdk:17.0.1-jdk-slim

COPY build/libs/service0-*.jar /app/test-deploy.jar

CMD ["java", "-jar", "/app/test-deploy.jar"]