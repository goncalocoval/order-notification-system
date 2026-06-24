# api-gateway 🔀

The single entry point for the order-notification-system. Routes incoming requests to the appropriate microservice based on the request path.

---

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
- **Spring Cloud Gateway** — request routing

---

## Routing

| Path | Destination |
|---|---|
| `/orders/**` | `order-service:8081` |
| `/notifications/**` | `notification-service:8082` |

---

## Running

The API Gateway is designed to run as part of the full system via Docker Compose from the root of the monorepo:

```bash
docker-compose up --build
```

The gateway will be available at `http://localhost:8080`.

---

## License

Do whatever you want with it.
