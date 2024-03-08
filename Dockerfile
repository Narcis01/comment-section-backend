# Use a base image with JDK and Maven
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build

# Set the working directory
WORKDIR /app

# Copy the source code into the container
COPY . .

# Run unit tests
RUN mvn clean test

# Build the application
RUN mvn clean install -DskipTests
CMD ["java", "-jar", "comment-section-0.0.1-SNAPSHOT.jar"]

