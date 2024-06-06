FROM maven:3.9.6-amazoncorretto-21-al2023 as builder

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM openjdk:21 as runner
COPY --from=builder /home/app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]