#Build stage
FROM maven:3.8.1-jdk-11-slim AS build
COPY pom.xml /notification-service/
COPY src /notification-service/src
RUN mvn -f /notification-service/pom.xml clean package

#Run stage
FROM openjdk:11-slim AS local
COPY --from=build /notification-service/target/notification-service*.jar /notification-service/notification-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-service/notification-service.jar"]