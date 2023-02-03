FROM openjdk:latest
USER root
ADD target/docker-training-1.0.0-SNAPSHOT.jar docker-training-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["sh","-c","java -jar docker-training-1.0.0-SNAPSHOT.jar"]