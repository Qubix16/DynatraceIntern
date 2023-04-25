FROM maven:3.9.1 AS build-stage

WORKDIR /maven

COPY ./ ./

RUN mvn package -DskipTests

FROM openjdk:17-jdk-alpine AS production-stage

WORKDIR /spring 

COPY --from=build-stage /maven/target/demo-0.0.1-SNAPSHOT.jar spring-app.jar

EXPOSE 8080

CMD ["java","-jar","spring-app.jar"]

