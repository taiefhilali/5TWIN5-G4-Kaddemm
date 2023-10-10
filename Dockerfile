FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/5TWIN5-G4-Kaddem.jar  5TWIN5-G4-Kaddem.jar
#COPY target/5TWIN5-G4-Kaddem.jar/5TWIN5-G4-Kaddem.jar
ENTRYPOINT ["java", "-jar", "/5TWIN5-G4-Kaddem.jar"]
