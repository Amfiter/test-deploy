FROM gradle:8.3 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
EXPOSE 5000
COPY --from=build /home/gradle/src/build/libs/test-deploy-*.jar /app/test-deploy.jar
ENTRYPOINT ["java","-jar","/app/test-deploy.jar"]