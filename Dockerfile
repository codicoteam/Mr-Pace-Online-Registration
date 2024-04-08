#FROM maven:3.8.5-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/SecurityPostgres-0.0.1-SNAPSHOT.jar SecurityPostgres.jar
#EXPOSE 8080
#
#ENV SPRING_DATA_MONGODB_URI=mongodb+srv://zpmakaza:clinpride@cluster0.ifpqavn.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0
#ENV SPRING_DATA_MONGODB_DATABASE=Java
#ENTRYPOINT ["java","-jar", "SecurityPostgres.jar"]
# Use a base image with Java Runtime Environment
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file of your Spring Boot application to the container
COPY target/SecurityPostgres-0.0.1-SNAPSHOT.jar SecurityPostgres.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8080

# Install MongoDB dependencies
RUN apt-get update && apt-get install -y \
    gnupg \
    wget

# Import the MongoDB public GPG key
RUN wget -qO - https://www.mongodb.org/static/pgp/server-5.0.asc | apt-key add -

# Add the MongoDB repository
RUN echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu bionic/mongodb-org/5.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-5.0.list

# Install MongoDB
RUN apt-get update && apt-get install -y \
    mongodb-org

# Copy the MongoDB configuration file to the container
COPY mongodb.conf /etc/mongodb.conf

# Start MongoDB service
RUN service mongod start

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "SecurityPostgres.jar"]