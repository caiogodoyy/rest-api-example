# Spring Boot REST API Exercise Project
Developed during the "Spring Boot 3: Developing a REST API in Java" course on Alura. The project focuses on building a simple RESTful API using Spring Boot.

## Spring Initializr Dependencies
- Spring Boot DevTools
- Lombok
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Flyway Migration
- Validation

## Requirements
- Java Development Kit (JDK) 17
- Apache Maven 3.8.4 +

## Installation
To set up the project locally, follow these steps:
1. Clone the repository to your local machine.
```
git clone https://github.com/caiogodoyy
```
2. Navigate to the project directory.
```
cd rest-api-template-java
```
3. Build the project using Maven.
```
mvn clean package -DskipTests
```

## Getting Started

Before you start the application, make sure that both the MySQL server and Jaeger are running. Additionally, ensure that there are two databases available: `alura` for the main application and `alura_test` for testing purposes.

### MySQL Server

Make sure your MySQL server is up and running. If you don't have it installed locally, you can use a Docker container. Here's an example command to run a MySQL container:

```bash
docker run --name mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql:latest
```

Adjust the parameters (such as passwords and database names) according to your preferences.

### Jaeger Tracing

Jaeger is used for distributed tracing within the application. You can run a Jaeger container using the following Docker command:

```bash
docker run --name jaeger -p 14250:14250 -p 16686:16686 -d jaegertracing/all-in-one
```

This will start Jaeger on port 16686 for the web interface and port 14250 for the Jaeger collector.

### Database Setup

Ensure that the required databases are created:

- Main Database: `alura`
- Test Database: `alura_test`

You can use your preferred MySQL client or run SQL commands to create the databases:

```sql
CREATE DATABASE alura;
CREATE DATABASE alura_test;
```

With the MySQL server and Jaeger running, and the databases set up, you're ready to run the application.

## Running the Application

### Option 1: Command Line

To run the application using the command line, follow these steps:

1. Open a terminal and navigate to the project directory.

2. Run the following command:

    ```bash
    java -jar \
    -DJWT_SECRET=<your_secret> \
    -DDATASOURCE_URL=<your_database_host>:<your_database_port> \
    -DDATASOURCE_USERNAME=<your_database_username> \
    -DDATASOURCE_PASSWORD=<your_database_password> \
    -DJAVA_TOOL_OPTIONS="-javaagent:./opentelemetry-javaagent.jar" \
    -DOTEL_SERVICE_NAME="rest-api-template-java" \
    -DOTEL_TRACES_EXPORTER="jaeger" \
    -DOTEL_EXPORTER_JAEGER_ENDPOINT=<your_jaeger_endpoint> \
    target/api-0.0.1-SNAPSHOT.jar

    ```

    Example:

    ```bash
    java -jar \
    -DJWT_SECRET=secret \
    -DDATASOURCE_URL=jdbc:mysql://localhost:3306/alura \
    -DDATASOURCE_USERNAME=root \
    -DDATASOURCE_PASSWORD=root \
    -DJAVA_TOOL_OPTIONS="-javaagent:./opentelemetry-javaagent.jar" \
    -DOTEL_SERVICE_NAME="rest-api-template-java" \
    -DOTEL_TRACES_EXPORTER="jaeger" \
    -DOTEL_EXPORTER_JAEGER_ENDPOINT=http://localhost:14250 \
    target/api-0.0.1-SNAPSHOT.jar

    ```

This will start the application with the specified environment variables.

### Option 2: Visual Studio Code (Using `launch.json`)

If you are using Visual Studio Code, you can run the application using the `launch.json` configuration. Follow these steps:

1. Open the `launch.json` file located in the `.vscode` directory.

2. Add the following environment variables to the `Run Local` configuration:

    ```json
    {
        "type": "java",
        "name": "Run Local",
        "request": "launch",
        "mainClass": "com.caio.alura.api.DegreeApplication",
        "projectName": "api",
        "env": {
            "JWT_SECRET": "<your_secret>",
            "DATASOURCE_URL": "<your_database_host>:<your_database_port>",
            "DATASOURCE_USERNAME": "<your_database_username>",
            "DATASOURCE_PASSWORD": "<your_database_password>",
            "JAVA_TOOL_OPTIONS": "-javaagent:./opentelemetry-javaagent.jar",
            "OTEL_SERVICE_NAME": "rest-api-template-java",
            "OTEL_TRACES_EXPORTER": "jaeger",
            "OTEL_EXPORTER_JAEGER_ENDPOINT": "<your_jaeger_endpoint>"
        }
    }
    ```

    Example:

    ```json
    {
        "type": "java",
        "name": "Run Local",
        "request": "launch",
        "mainClass": "com.caio.alura.api.DegreeApplication",
        "projectName": "api",
        "env": {
            "JWT_SECRET": "secret",
            "DATASOURCE_URL": "jdbc:mysql://localhost:3306/alura",
            "DATASOURCE_USERNAME": "root",
            "DATASOURCE_PASSWORD": "root",
            "JAVA_TOOL_OPTIONS": "-javaagent:./opentelemetry-javaagent.jar",
            "OTEL_SERVICE_NAME": "rest-api-template-java",
            "OTEL_TRACES_EXPORTER": "jaeger",
            "OTEL_EXPORTER_JAEGER_ENDPOINT": "http://localhost:14250"
        }
    }
    ```

3. Save the `launch.json` file.

This will launch the application with the specified environment variables using the `launch.json` configuration in Visual Studio Code.

Choose the method that best suits your workflow, and access the API at http://localhost:8080.
