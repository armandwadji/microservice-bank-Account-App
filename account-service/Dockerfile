# Generate Build
FROM maven:3.6.3-openjdk-17-slim AS MAVEN_BUILD
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Dockerize
FROM openjdk:17-oracle
WORKDIR /app
VOLUME /tmp
COPY --from=MAVEN_BUILD /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]