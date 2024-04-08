FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/SecurityPostgres-0.0.1-SNAPSHOT.jar SecurityPostgres.jar
EXPOSE 8080

ENV SPRING_DATA_MONGODB_URI=mongodb+srv://zpmakaza:clinpride@cluster0.ifpqavn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
ENV SPRING_DATA_MONGODB_DATABASE=Java
ENTRYPOINT ["java","-jar", "SecurityPostgres.jar"]
