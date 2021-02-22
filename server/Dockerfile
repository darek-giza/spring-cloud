# Build stage
FROM maven:3.6.3-jdk-11-slim AS build
COPY pom.xml /server/
COPY src /server/src
RUN mvn -f /server/pom.xml clean package

# Run stage
FROM openjdk:11-jdk-slim AS local
COPY --from=build /server/target/server*.jar /server/server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/server/server.jar"]
