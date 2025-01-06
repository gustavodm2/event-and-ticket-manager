FROM eclipse-temurin:17-jdk-jammy

WORKDIR /src

CMD ["mvn", "clean", "package"]

COPY target/ms-event-manager-0.0.1-SNAPSHOT.jar ms-event-manager.jar

EXPOSE 8080

CMD ["java", "-jar", "ms-event-manager.jar"]
