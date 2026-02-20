🚀 Product API – Spring Boot Backend Assignment
<p align="center">














</p>
📖 Overview

This project is a secure Spring Boot REST API implementing:

🔐 JWT Authentication

♻️ Refresh Token Mechanism

👥 Role-based Authorization (ADMIN / USER)

📦 Product CRUD APIs

🗄️ H2 In-Memory Database

📑 Swagger API Documentation

🐳 Docker Support

🧰 Tech Stack
Layer	Technology
Language	Java 17
Framework	Spring Boot 3
Security	Spring Security + JWT
Persistence	Spring Data JPA
Database	H2
Build Tool	Maven
API Docs	Swagger (OpenAPI)
Container	Docker
📂 Project Structure
com.example.assignment
├── config
├── controller
├── dto
├── entity
├── repository
├── service
└── security

▶️ Run Application
🔨 Build
mvn clean install

▶️ Run
mvn spring-boot:run


🌐 Application URL

http://localhost:8080

🗄️ H2 Database

🖥️ Console

http://localhost:8080/h2-console

Property	Value
JDBC URL	jdbc:h2:mem:testdb
User	sa
Password	(empty)
📑 Swagger API Docs

Swagger UI

http://localhost:8080/swagger-ui.html


OpenAPI JSON

http://localhost:8080/v3/api-docs

🔐 Authentication APIs
📝 Register

POST /auth/register

{
"username": "admin",
"password": "admin123",
"role": "ADMIN"
}

🔑 Login

POST /auth/login

{
"username": "admin",
"password": "admin123"
}


✅ Response

{
"accessToken": "jwt",
"refreshToken": "token"
}

♻️ Refresh Token

POST /auth/refresh

{
"refreshToken": "token"
}

👨‍💻 Author : 
Aravindsamy
Java Backend Developer