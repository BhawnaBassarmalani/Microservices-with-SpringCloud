FROM eclipse-temurin:17

LABEL maintainer:"bassarmalani.b@northeastern.edu"

WORKDIR /app

COPY target/spring-boot-restful-webservices-0.0.1-SNAPSHOT.jar /app/target/spring-boot-restful-webservices.jar

ENTRYPOINT ["java" , "-jar" , "spring-boot-restful-webservices.jar"]