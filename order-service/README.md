# order-service 📦

A REST API responsible for managing orders. Publishes an event to RabbitMQ on every order creation or status update, which is consumed by the notification-service.

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
- **Spring Web** — REST API
- **Spring Data JPA** — database access
- **PostgreSQL** — relational database
- **RabbitMQ (AMQP)** — event publishing
- **Bean Validation** — input validation
- **SpringDoc OpenAPI** — Swagger UI

---

## Features

- 📦 Create and manage orders
- 🔄 Update order status (`CREATED` → `PROCESSING` → `SHIPPED` → `DELIVERED`)
- 📤 Publishes `OrderEvent` to RabbitMQ on every state change
- 🛡️ Input validation with meaningful error messages
- 🌐 Global error handling with clean JSON responses
- 📖 Swagger UI for interactive API documentation

---

## API Endpoints

> When running with Docker Compose, access via API Gateway on port `8080`.  
> When running standalone, access directly on port `8081`.

### `POST /orders`

Creates a new order.

**Request body**
```json
{
  "customerName": "Gonçalo Coval",
  "customerEmail": "user@example.com",
  "product": "Mechanical Keyboard",
  "quantity": 1
}
```

**Example response** `201 Created`
```json
{
  "id": 1,
  "customerName": "Gonçalo Coval",
  "customerEmail": "user@example.com",
  "product": "Mechanical Keyboard",
  "quantity": 1,
  "status": "CREATED",
  "createdAt": "2026-06-24T23:00:00",
  "updatedAt": "2026-06-24T23:00:00"
}
```

---

### `GET /orders`

Returns a list of all orders.

---

### `GET /orders/{id}`

Returns a single order by ID.

---

### `PATCH /orders/{id}/status`

Updates the status of an order.

**Request body**
```json
{
  "status": "PROCESSING"
}
```

---

### Error Responses

| Status | Description |
|---|---|
| `400` | Missing or invalid request body |
| `404` | Order not found |
| `500` | Unexpected server error |

---

## Event Publishing

On every `POST /orders` or `PATCH /orders/{id}/status`, an `OrderEvent` is published to RabbitMQ:

```json
{
  "orderId": 1,
  "customerName": "Gonçalo Coval",
  "customerEmail": "user@example.com",
  "product": "Mechanical Keyboard",
  "status": "CREATED",
  "timestamp": "2026-06-24T23:00:00"
}
```

---

## Swagger UI

```
http://localhost:8081/swagger-ui/index.html
```

---

## Project Structure

```
src/main/java/com/example/orderservice/
│
├── OrderServiceApplication.java
│
├── controller/
│   └── OrderController.java
│
├── service/
│   └── OrderService.java
│
├── repository/
│   └── OrderRepository.java
│
├── model/
│   ├── Order.java
│   └── OrderStatus.java
│
├── dto/
│   ├── OrderRequest.java
│   ├── OrderStatusRequest.java
│   └── OrderResponse.java
│
├── event/
│   └── OrderEvent.java
│
├── exception/
│   ├── OrderNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── config/
    └── RabbitMQConfig.java
```

---

## License

Do whatever you want with it.
