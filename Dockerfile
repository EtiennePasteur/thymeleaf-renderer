FROM openjdk:17-jdk-slim

# Install Node.js and npm
RUN apt-get update && apt-get install -y curl && \
    curl -sL https://deb.nodesource.com/setup_16.x | bash && \
    apt-get install -y nodejs

# Install Gulp globally
RUN npm install -g gulp-cli

# Copy Spring Boot application and Gulp files
COPY . /app
WORKDIR /app

# Install project dependencies for Gulp
RUN npm install

# Build Spring Boot project (Java)
RUN ./mvnw clean package

# Expose ports for Spring Boot and Live Reload
EXPOSE 8080 35729

# Start Gulp and Spring Boot
CMD ["sh", "-c", "gulp & java -jar target/thymeleaf-renderer-0.0.1-SNAPSHOT.jar"]
