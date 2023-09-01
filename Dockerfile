# Use the official Java 8 base image
FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/Katademo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
