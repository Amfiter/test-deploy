FROM openjdk:17.0.1-jdk-slim

COPY ./test-deploy-*.jar /app/test-deploy.jar

CMD ["java", "-jar", "/app/test-deploy.jar"]