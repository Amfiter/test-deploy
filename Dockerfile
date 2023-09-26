FROM openjdk:17.0.1-jdk-slim

# Create a directory
WORKDIR /app

# Copy all the files from the current directory to the image
COPY . .

# build the project avoiding tests
RUN ./gradlew clean build -x test

# Run the jar file
CMD ["java", "-jar", "./build/libs/test-deploy-0.0.1-SNAPSHOT.jar"]