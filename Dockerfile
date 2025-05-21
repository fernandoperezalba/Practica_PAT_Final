# -------- Stage 1: Build the application --------
FROM openjdk:11-jdk AS build
WORKDIR /app
LABEL authors="fpa20"

# Copy Maven config and source code
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src

# Grant permissions to Maven wrapper and build the JAR
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# -------- Stage 2: Run the application --------
FROM openjdk:11-jdk
VOLUME /tmp

# Copy the JAR file built in Stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose port and run app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
