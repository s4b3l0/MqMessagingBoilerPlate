# Use OpenJDK 21 as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the docker-compose.yml file into the container
COPY docker-compose.yml /app/docker-compose.yml

# Copy the Gradle wrapper and build files
COPY gradlew gradlew.bat /app/
COPY gradle/ /app/gradle/
COPY build.gradle settings.gradle /app/

# Copy the source code
COPY src/ /app/src/

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build -x test

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]

