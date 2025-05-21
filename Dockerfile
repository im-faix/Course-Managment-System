FROM openjdk:21-jdk
WORKDIR /app
COPY target/*.jar target/app.jar
ENTRYPOINT ["java", "-jar", "target/app.jar"]

