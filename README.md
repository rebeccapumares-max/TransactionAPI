# 💳 PayGuard Transaction API

A Spring Boot backend application that processes financial transactions with validation and business rules.

## 🚀 Features

* Process deposits, withdrawals, and transfers
* Input validation using Spring Validation
* Prevent self-transfers
* Prevent overdrafts (insufficient balance)
* Proper HTTP status handling (201, 400, 409, 422)
* In-memory balance tracking

## 🛠 Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database

## 📌 API Endpoint

### POST /api/process

Example request:

```json
{
  "sourceAccountId": "ACC-100",
  "targetAccountId": "ACC-200",
  "amount": 50.00,
  "type": "TRANSFER"
}
```

## ✅ Example Responses

* 201 Created → successful transaction
* 400 Bad Request → invalid input
* 409 Conflict → self-transfer
* 422 Unprocessable Entity → insufficient balance

## ▶️ How to Run

1. Clone repo
2. Open in IntelliJ
3. Run `TransactionApiApplication`
4. Send POST requests to `http://localhost:8080/api/process`

## 📚 What I Learned

* Building REST APIs with Spring Boot
* Layered architecture (Controller, Service, Repository)
* Validation and exception handling
* Designing business rules in backend systems
