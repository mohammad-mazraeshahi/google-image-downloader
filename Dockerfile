FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /build
ARG ARTIFACT_VERSION
COPY . .
RUN mvn clean package -DskipTests

FROM  ubuntu/jre:17-22.04_edge as runner
WORKDIR /app
ARG ARTIFACT_VERSION
COPY --from=builder /build/target/*.jar /app/final-artifact.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/final-artifact.jar"]