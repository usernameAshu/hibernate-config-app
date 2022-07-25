FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/multi-datasource-app.jar
COPY ${JAR_FILE} multi-datasource-docker-app.jar
ENTRYPOINT ["java","-jar","/multi-datasource-docker-app.jar"]