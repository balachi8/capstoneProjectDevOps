# base image from Docker Hub
FROM maven:3.8.3-openjdk-17

#Set user privilege
RUN groupadd -r appgroup && useradd -r -g appgroup -u 1001 appuser

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and the project files to the container
COPY --chown=appuser:appgroup pom.xml .
COPY --chown=appuser:appgroup src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Stage 2: Minimal runtime image
FROM alpine:latest

# Copy the built JAR file from the previous stage to the container
COPY --chown=appuser:appgroup target/rest-service-0.0.1-SNAPSHOT.jar /app/rest-service-0.0.1-SNAPSHOT.jar

#Switch to the non-root user
USER 1001

#Expose the port your application runs on
EXPOSE 9090

# Set the command to run the application
CMD ["java", "-jar", "rest-service-0.0.1-SNAPSHOT.jar"]
