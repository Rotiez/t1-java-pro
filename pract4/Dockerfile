FROM openjdk:21-slim

ARG JAR_FILE=build/libs/pract4.jar

COPY ${JAR_FILE} /pract4.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/pract4.jar"]

LABEL image="pract4:1.0.0"