# Alura Case - Spring Boot App

This application was developed to address the Alura Case.

## Requirements

For building and running the application you need:

- [JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- [Maven 3.8.7](https://maven.apache.org)
- [Docker](https://docs.docker.com/engine/install/)

## Running the application locally

### Expose the MySQL Database
```shell
docker compose up -d
```

### Running aplication
```shell
mvn spring-boot:run
```

## Postman Collection

- [Json Collection](alura.postman_collection.json)