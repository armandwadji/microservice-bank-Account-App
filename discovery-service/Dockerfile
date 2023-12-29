FROM maven:3.6.3-openjdk-17 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -DskipTests
FROM openjdk:17-oracle
VOLUME /tmp
COPY --from=MAVEN_BUILD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]