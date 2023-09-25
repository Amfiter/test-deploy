FROM gradle:8.3 AS build
ADD . .
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /home/gradle/build/libs/test-deploy-*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]