FROM gradle:8-jdk17 AS builder
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle gradle
COPY src src
RUN gradle build --no-daemon -x test

FROM azul/zulu-openjdk-alpine:17-latest
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app/app.jar"]


