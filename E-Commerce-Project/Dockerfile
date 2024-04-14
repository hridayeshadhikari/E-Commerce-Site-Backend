FROM maven:3.8.5-openjdk:17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/spring-boot-ecommerce.jar spring-boot-ecommerce.jar
EXPOSE 1213
ENTRYPOINT ["java","-jar","spring-boot-ecommerce.jar"]