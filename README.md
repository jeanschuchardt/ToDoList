# Task list 
The application's target audience is people who need to create lists of items organized into sub-items (optional) and who want to simply share this list with others so that they can collaborate.

This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

## Features

In this API we have 3 main entities: user, list and item. The relationship would be:

- Create user registration and login with JWT authentication
- Password encryption using BCrypt
- User can create multiple lists
- List has one or more items;
- Item is associated with only one list;
- Item can be associated with another item;
- Item is associated with a user that may not be the same as the owner of the list to which this item belongs.



## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* Swagger
* Lombok 
* Mysql 

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:


* Build the project: mvn clean install -DskipTests=true
* Create docker image: docker build -t task .
* Instantiate a database and start the application: docker-compose up -d
* Shutdown the application: docker-compose down -v


-> The application will be available at http://localhost:6161.
-> Swagger endpoint http://localhost:6161/swagger-ui/index.html#/

