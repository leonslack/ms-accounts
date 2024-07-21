FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/ms-accounts-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
