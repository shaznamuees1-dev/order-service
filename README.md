# Order Service – Spring Boot Backend

A production-ready Order Management REST API built using Spring Boot.

This project demonstrates RESTful API design, layered architecture,
validation, exception handling, logging, testing, and Docker containerization.

---

## Project Overview

The Order Service supports:

- Create Order
- Fetch All Orders
- Fetch Order by ID
- Update Order
- Update Order Status
- Delete Order

Architecture layers:

- Controller Layer
- Service Layer
- Repository Layer
- DTO Pattern
- Global Exception Handling

---

## Technology Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (file-based)
- Maven
- Docker
- JUnit 5
- SLF4J Logging

---

## Running the Application (Local Development)

### 1. Build the Project

mvn clean package

### 2. Run the Application

mvn spring-boot:run

Application runs at:

http://localhost:8080

---

## Running with Docker

### 1. Build the Project

mvn clean package

### 2. Build Docker Image

docker build -t order-service:latest .

### 3. Run Container

docker run -p 8081:8080 order-service:latest

Application runs at:

http://localhost:8081

---

## H2 Database Console

Access:

http://localhost:8081/h2-console

Configuration:

JDBC URL: jdbc:h2:file:./data/orderdb  
Username: sa  
Password: (leave empty)

---

## API Endpoints

Create Order  
POST /orders

Get All Orders  
GET /orders

Get Order by ID  
GET /orders/{id}

Update Order  
PUT /orders/{id}

Update Order Status  
PUT /orders/{id}/status

Delete Order  
DELETE /orders/{id}

---

## Testing

Run all tests:

mvn test

Includes:

- Controller Tests (MockMvc)
- Service Tests
- Spring Boot Context Test

---

## Features Implemented

- CRUD Operations
- DTO Pattern
- Validation
- Global Exception Handling
- Logging
- Unit & Integration Tests
- Dockerized Application
- Persistent H2 Database

---

## Author

Backend implementation by  
Shazna Muees

---

## License

This project is created for educational and learning purposes.
