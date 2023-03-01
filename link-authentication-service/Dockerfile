FROM openjdk:11-jdk
COPY /target/*.jar  auth.jar
ENTRYPOINT ["java", "-jar", "/auth.jar"]