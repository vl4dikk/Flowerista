FROM maven:3.8.5-openjdk-17
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/flowerista-be-0.0.1-SNAPSHOT.jar flowerista-be.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","flowerista-be.jar"]