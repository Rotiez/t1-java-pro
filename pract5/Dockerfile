FROM openjdk:21-slim

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} pract5.jar

EXPOSE 8080:8080

ENTRYPOINT ["java","-jar","/pract5.jar"]

LABEL image="pract5:1.0.0"