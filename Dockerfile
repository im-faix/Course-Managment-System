FROM ubuntu
WORKDIR /app
RUN apt-get update && apt-get install maven openjdk-21-jdk -y
RUN mvn clean package
COPY target/*.jar target/app.jar
ENTRYPOINT ["java", "-jar", "target/app.jar"]

