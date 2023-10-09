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

## Run local

## Run docker
To run the application using Docker, execute the following commands:
1. First, start a MySQL container with the following command:
```
docker run --name container-name -p 3306:3306 -v local/path/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql
```
2. Next, build and run the API container:
```
docker build -t image-name .
docker run --name container-name -p 8080:8080 image-name
```
Now, you can access the API at http://localhost:8080.
