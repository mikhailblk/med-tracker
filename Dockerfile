# Build-Stage mit Maven
FROM docker.io/eclipse-temurin:21-jdk-alpine AS build

# Maven installieren
RUN apk add --no-cache maven

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime-Stage
FROM docker.io/eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]