# notification-service 🔔

A REST API responsible for consuming order events from RabbitMQ and storing notifications. Listens to the `orders.queue` and generates a human-readable message for every order state change.

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
- **Spring Web** — REST API
- **Spring Data JPA** — database access
- **PostgreSQL** — relational database
- **RabbitMQ (AMQP)** — event consumption
- **SpringDoc OpenAPI** — Swagger UI

---

## Features

- 🔔 Listens to RabbitMQ and processes order events automatically
- 💬 Generates human-readable notification messages
- 🔍 Query notifications by order ID
- 🌐 Global error handling with clean JSON responses
- 📖 Swagger UI for interactive API documentation

---

## API Endpoints

> When running with Docker Compose, access via API Gateway on port `8080`.  
> When running standalone, access directly on port `8082`.

### `GET /notifications`

Returns a list of all notifications.

**Example response** `200 OK`
```json
[
  {
    "id": 1,
    "orderId": 1,
    "customerName": "Gonçalo Coval",
    "customerEmail": "user@example.com",
    "product": "Mechanical Keyboard",
    "status": "CREATED",
    "message": "Order #1 for Mechanical Keyboard has been CREATED.",
    "createdAt": "2026-06-24T23:00:00"
  }
]
```

---

### `GET /notifications/order/{orderId}`

Returns all notifications for a specific order.

---

## Event Consumption

The service listens to the `orders.queue` and processes `OrderEvent` messages automatically. For each event received, a notification is created and stored in the database.

```
OrderEvent received
  → generates message: "Order #1 for Mechanical Keyboard has been CREATED."
  → saves Notification to notifications_db
```

---

## Swagger UI

```
http://localhost:8082/swagger-ui/index.html
```

---

## Project Structure

```
src/main/java/com/example/notificationservice/
│
├── NotificationServiceApplication.java
│
├── controller/
│   └── NotificationController.java
│
├── service/
│   └── NotificationService.java
│
├── repository/
│   └── NotificationRepository.java
│
├── model/
│   └── Notification.java
│
├── dto/
│   └── NotificationResponse.java
│
├── event/
│   ├── OrderEvent.java
│   └── OrderEventListener.java
│
├── exception/
│   ├── NotificationNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── config/
    └── RabbitMQConfig.java
```

---

## License

Do whatever you want with it.
