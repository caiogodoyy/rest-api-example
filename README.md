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

## Run Locally

To run the application, ensure that the MySQL server is running either locally or in a container, and that there exists a table called `alura` and another table called `alura_test`.

#### First Way:

Execute the following command in the project directory:

```bash
java -jar -DJWT_SECRET=<your_secret> -DDATASOURCE_URL=<your_database_host>:<your_database_port> -DDATASOURCE_USERNAME=<your_database_username> -DDATASOURCE_PASSWORD=<your_database_password> target/api-0.0.1-SNAPSHOT.jar
```

#### Second Way:

1. Add the environment variables in the `launch.json` settings in the `.vscode` directory.

   ```json
   {
       "type": "java",
       "name": "Run Local",
       "request": "launch",
       "mainClass": "com.caio.alura.api.DegreeApplication",
       "projectName": "rest-api-template-java",
       "env": {
           "JWT_SECRET": "<your_secret>",
           "DATASOURCE_URL": "<your_database_host>:<your_database_port>",
           "DATASOURCE_USERNAME": "<your_database_username>",
           "DATASOURCE_PASSWORD": "<your_database_password>"
       }
   }
   ```

2. Run locally using the VSCode menu:
   - Right-click the DegreeApplication project folder in the Explorer view.
   - Select Run Java.

Ensure that the MySQL server is up and running before executing the application. Choose the method that best suits your workflow, and access the API at http://localhost:8080.

## Run docker

To run the application, ensure that the MySQL server is running either locally or in a container, and that there exists a table called `alura` and another table called `alura_test`.

Execute the following commands in the project directory:

```bash
docker build -t api-template-image .
docker run --name api-template -p 8080:8080 -e JWT_SECRET=<your_secret> -e DATASOURCE_URL=<your_database_host>:<your_database_port> -e DATASOURCE_USERNAME=<your_database_username> -e DATASOURCE_PASSWORD=<your_database_password> api-template-image
```
Now, you can access the API at http://localhost:8080.
