FROM maven:3-eclipse-temurin-20-alpine AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

EXPOSE 8080

ENTRYPOINT ["java","-jar","/home/app/target/alura-0.0.1-SNAPSHOT.jar.jar"]
