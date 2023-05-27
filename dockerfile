FROM maven:latest as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/library-service-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "library-service-0.0.1-SNAPSHOT.jar"]