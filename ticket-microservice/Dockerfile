#FROM openjdk:17
#COPY target/ticket-microservice-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.9.2 AS builder
WORKDIR /opt/app
COPY . .
COPY ./target/ticket-microservice-0.0.1-SNAPSHOT.jar /opt/app/
RUN mvn install:install-file -Dfile=/opt/app/ticket-microservice-0.0.1-SNAPSHOT.jar
#RUN mvn install -Dmaven.test.skip




FROM openjdk:17-slim

WORKDIR /opt/app
#EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar

ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]