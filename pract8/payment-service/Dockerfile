FROM openjdk:21-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} payment-service.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/payment-service.jar"]

LABEL image="payment-service:1.0.0"