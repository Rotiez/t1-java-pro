FROM openjdk:21-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} limit-service.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/limit-service.jar"]

LABEL image="limit-service:1.0.0"