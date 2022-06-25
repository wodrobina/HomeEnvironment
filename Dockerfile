#
# Build stage
#
FROM maven:3.8.3-openjdk-17 as build
COPY . /usr/app
WORKDIR /usr/app
RUN mvn clean package

#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /usr/app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${SPRING_PROFILE}","-Dspring.datasource.username=${DB_USER}","-Dspring.datasource.password=${DB_PASSWORD}","-Dspring.datasource.url=${DATASOURCE_URL}","app.jar"]