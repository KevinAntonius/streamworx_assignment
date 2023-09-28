FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /home/app/target/demoAPI2-0.0.1-SNAPSHOT.jar /usr/local/lib/demoAPI2.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/demoAPI2.jar"]
EXPOSE 8080