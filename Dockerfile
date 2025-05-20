FROM ubuntu AS stage
WORKDIR /app
COPY . .
RUN apt-get update && apt-get install openjdk-11-jdk  maven -y
RUN mvn clean package
RUN mv -f target/*.jar target/app.jar

FROM openjdk:17-jdk-slim
WORKDIR /basic
COPY --from=stage /app/target/app.jar .
CMD ["java" ,"-jar","target/app.jar"]

FROM mcp/postgresql
WORKDIR /app

