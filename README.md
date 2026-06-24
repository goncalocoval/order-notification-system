# order-notification-system 📦

> just another event-driven microservices system.

A distributed system built with Java and Spring Boot that manages orders and sends notifications using an event-driven architecture. Services communicate asynchronously via RabbitMQ and are exposed through a single API Gateway.

---

## Architecture

```
Client
  ↓
API Gateway (:8080)
  ├── /orders/**         → order-service (:8081)
  └── /notifications/**  → notification-service (:8082)

order-service (:8081)
  ├── PostgreSQL (orders_db)
  └── Publishes events → RabbitMQ

notification-service (:8082)
  ├── PostgreSQL (notifications_db)
  └── Consumes events ← RabbitMQ
```

---

## Services

| Service | Port | Responsibility |
|---|---|---|
| [api-gateway](./api-gateway) | 8080 | Single entry point, routes requests to services |
| [order-service](./order-service) | 8081 | Manages orders, publishes events to RabbitMQ |
| [notification-service](./notification-service) | 8082 | Consumes events, stores notifications |

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
- **Spring Cloud Gateway** — API Gateway
- **Spring Data JPA** — database access
- **PostgreSQL** — relational database (one per service)
- **RabbitMQ** — message broker for async communication
- **Docker + Docker Compose** — containerization and orchestration

---

## Event Flow

```
1. Client creates or updates an order via API Gateway
2. order-service saves the order and publishes an OrderEvent to RabbitMQ
3. notification-service receives the event and stores a notification
4. Client can query notifications via API Gateway
```

Every order status change (`CREATED`, `PROCESSING`, `SHIPPED`, `DELIVERED`) triggers a new event and generates a new notification.

---

## Getting Started

### Prerequisites

- [Docker](https://www.docker.com/) installed and running
- [PostgreSQL](https://www.postgresql.org/) installed and running locally
- Two databases created:

```sql
CREATE DATABASE orders_db;
CREATE DATABASE notifications_db;
```

### Run

```bash
docker-compose up --build
```

All services will start automatically. The API Gateway will be available at `http://localhost:8080`.

---

## API Endpoints

All requests go through the API Gateway on port `8080`.

### Orders

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/orders` | Create a new order |
| `GET` | `/orders` | Get all orders |
| `GET` | `/orders/{id}` | Get order by ID |
| `PATCH` | `/orders/{id}/status` | Update order status |

### Notifications

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/notifications` | Get all notifications |
| `GET` | `/notifications/order/{orderId}` | Get notifications by order ID |

---

## Order Status Flow

```
CREATED → PROCESSING → SHIPPED → DELIVERED
```

Each status change publishes an event to RabbitMQ and generates a notification.

---

## RabbitMQ Management

The RabbitMQ management panel is available at:

```
http://localhost:15672
```

Default credentials: `guest` / `guest`

---

## Project Structure

```
order-notification-system/
│
├── docker-compose.yml
├── README.md
│
├── api-gateway/
├── order-service/
└── notification-service/
```

---

## License

Do whatever you want with it.
