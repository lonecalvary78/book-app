FROM maven:3.9.6-eclipse-temurin-21 AS builder
COPY ./src ./src
COPY pom.xml .
RUN mvn clean install -U -DskipTests

FROM eclipse-temurin:21-jre
ENV TARGET_ENV="dev";
COPY --from=builder target/quarkus-app/lib/ /book/lib/
COPY --from=builder target/quarkus-app/*.jar /book/
COPY --from=builder target/quarkus-app/app/ /book/app/
COPY --from=builder target/quarkus-app/quarkus/ /book/quarkus/

EXPOSE 8080
ENTRYPOINT ["java","-jar","/book/quarkus-run.jar","-Dquarkus.http.port=8080","-Dquarkus.profile=$TARGET_ENV"]