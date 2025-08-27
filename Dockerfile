FROM openjdk:17-jre-slim

WORKDIR /app

ARG JAR_FILE=target/call-panel.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app.jar" ]