FROM eclipse-temurin:17-jdk-jammy

WORKDIR /src

COPY target/ms-ticket-manager-0.0.1-SNAPSHOT.jar ms-ticket-manager.jar

EXPOSE 8081

CMD ["mvn", "clean", "package"]

CMD ["java", "-jar", "ms-ticket-manager.jar"]
