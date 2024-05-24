
 FROM openjdk:17.0.1-jdk-slim
 ADD target/SecurityPostgres.jar SecurityPostgres.jar
 ENTRYPOINT ["java","-jar", "SecurityPostgres.jar"]

