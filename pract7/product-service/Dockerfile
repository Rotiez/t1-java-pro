FROM openjdk:21-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} product-service.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/product-service.jar"]

LABEL image="product-service:1.0.0"