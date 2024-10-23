# Thymeleaf Renderer

## Overview
Thymeleaf Renderer is a Spring Boot application that uses Thymeleaf for server-side rendering. It also incorporates Gulp for front-end build tasks.

## DockerHub
If you want to avoid building the docker image yourself, the container is also available on DockerHub. You can pull the image using the following command:
```sh
docker pull etiennepasteur/thymeleaf-renderer
```

## Prerequisites
- Docker
- Java 17
- Node.js and npm

## Building the Docker Image
To build the Docker image, run the following command in the root directory of the project:
```sh
docker build -t thymeleaf-renderer .
```

## Running the Docker Container
To run the Docker container, use the following command:
```sh
docker run -p 8080:8080 \
           -p 35729:35729 \
           -v $(pwd)/templates/:/app/src/main/resources/templates \
           -v $(pwd)/static/:/app/src/main/resources/static \
           -v $(pwd)/data.json:/app/src/main/resources/static/data.json \
           -v $(pwd)/translations.json:/app/src/main/resources/static/translations.json \
           thymeleaf-renderer
```

### Explanation:
- `-p 8080:8080`: Maps port 8080 on your local machine to port 8080 in the container for the Spring Boot application.
- `-p 35729:35729`: Maps port 35729 on your local machine to port 35729 in the container for Live Reload.
- `-v $(pwd)/templates/:/app/src/main/resources/templates`: Mounts the local directory containing your Thymeleaf templates.
- `-v $(pwd)/static/:/app/src/main/resources/static`: Mounts the local directory containing your static files (ex: css, js, ...).
- `-v $(pwd)/data.json:/app/src/main/resources/static/data.json`: Mounts the local JSON file containing your data (ex: variables, path, ...).
- `-v $(pwd)/translations.json:/app/src/main/resources/static/translations.json`: Mounts the local JSON file containing your translations.

## Accessing the Application
Once the container is running, you can access the application at:
- [http://localhost:8080/thymeleaf-renderer/{filename}](http://localhost:8080/thymeleaf-renderer/)

## Development
For development, you can use the following commands to build and run the application locally:
```sh
./mvnw clean package
java -jar target/thymeleaf-renderer-0.0.1-SNAPSHOT.jar
```

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.