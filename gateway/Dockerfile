#Build stage
FROM maven:3.6.3-openjdk-14-slim AS build
COPY pom.xml /gateway/
COPY src /gateway/src
RUN mvn -f /gateway/pom.xml clean package

#Run stage
FROM openjdk:14-slim AS local
COPY --from=build /gateway/target/gateway*.jar /gateway/gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway/gateway.jar"]