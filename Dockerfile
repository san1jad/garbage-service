FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} garbage-service.jar
ENTRYPOINT ["java", "-jar", "garbage-service.jar"]
EXPOSE 9091