FROM openjdk:18
EXPOSE 8080
COPY target/sixLetterAPI-0.0.1-SNAPSHOT.jar sixLetterAPI-0.1.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/sixLetterAPI-0.1.1-SNAPSHOT.jar"]