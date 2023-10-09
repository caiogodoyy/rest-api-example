FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

ENTRYPOINT ["java","-jar","target/api-0.0.1-SNAPSHOT.jar"]
