# Build stage
FROM maven:3.8.1-jdk-11-slim AS build
COPY pom.xml /user-service/
COPY src /user-service/src
RUN mvn -f /user-service/pom.xml clean package

# Run stage
FROM openjdk:11-slim AS local
COPY --from=build /user-service/target/user-service*.jar /user-service/user-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-service/user-service.jar"]