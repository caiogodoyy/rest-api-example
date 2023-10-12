# Spring Boot REST API Exercise Project
Developed during the "Spring Boot 3: Developing a REST API in Java" course on Alura. The project focuses on building a simple RESTful API using Spring Boot.

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

To run the application locally, ensure that the MySQL server is running either locally or in a container.

#### First Way:

Execute the following command in the project directory:

```bash
java -jar -DJWT_SECRET=eyJhbGciOiJIUzI1NiJ9 -DDOMAIN=localhost:3306 target/api-0.0.1-SNAPSHOT.jar
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
           "JWT_SECRET": "eyJhbGciOiJIUzI1NiJ9",
           "DOMAIN": "localhost:3306"
       }
   }
   ```

2. Run locally using the VSCode menu:
   - Select the configuration "Run Local" from the VSCode toolbar.
   - Start the debugging session.

Ensure that the MySQL server is up and running before executing the application. Choose the method that best suits your workflow, and access the API at http://localhost:8080.

## Run docker
To run the application using Docker, execute the following commands in the project directory:
1. Create a Docker network:
```
docker network create api-template-network
```
2. Start a MySQL container:
```
docker run --name api-template-mysql --network api-template-network -p 3306:3306 -v $(pwd)/init.sql:/data/app/init.sql -e MYSQL_ROOT_PASSWORD=root -d mysql --init-file /data/app/init.sql
```
3. Build and run the API container:
```
docker build -t api-template-image .
docker run --name api-template --network api-template-network -p 8080:8080 -e JWT_SECRET=eyJhbGciOiJIUzI1NiJ9 -e DOMAIN=api-template-mysql:3306 api-template-image
```
Now, you can access the API at http://localhost:8080.
