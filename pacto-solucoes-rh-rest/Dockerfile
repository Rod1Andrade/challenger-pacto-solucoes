FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .

RUN mvn dependency:go-offline

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:23-ea-17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/pacto-solucoes-rh-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]