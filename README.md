# doodle-task

A meeting scheduler REST API built with Java and Spring Boot, following Hexagonal Architecture and Domain-Driven Design principles.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 26 |
| Framework | Spring Boot 4.0.5 |
| Database | PostgreSQL |
| Data Access | Spring Data JDBC |
| Documentation | Swagger UI (SpringDoc OpenAPI) |
| Build Tool | Gradle |
| Testing | JUnit 5, TestContainers |

## Architecture

The project follows **Hexagonal Architecture (Ports & Adapters)** with **Domain-Driven Design (DDD)**:

- **`domain/`** — Pure business objects and port interfaces. No framework dependencies.
- **`application/`** — Application services that implement use cases and orchestrate domain logic.
- **`adapter/in/web/`** — REST controllers that translate HTTP requests into domain commands.
- **`adapter/out/persistence/`** — JPA-based persistence adapters that translate domain models to/from database entities.

## How to Run

**Prerequisites:** Docker, Java 26, Gradle

**1. Start the database**

```bash
docker-compose up -d
```

This spins up a PostgreSQL instance on port `5432`.

**2. Start the application**

```bash
./gradlew bootRun
```

The service will be available at [http://localhost:8080](http://localhost:8080).

**3. Run the tests**

```bash
./gradlew test
```

Test uses TestContainers, so Docker must be running.

**4. Stop everything**

```bash
# Stop the app
Ctrl+C

# Bring down the database
docker-compose down
```

## Future Improvements

### 1. Authentication & Authorization
Introduce OAuth2 via Keycloak as the identity provider. Spring Security's OAuth2 resource server support makes this straightforward to wire in. This would protect all endpoints.

### 2. Metrics & Observability
Expose application metrics via Spring Boot Actuator + Micrometer, scraped by Prometheus and visualised in Grafana.

### 3. Structured Logging
Adopt SLF4J + Logback so that log output is machine-readable.

### 4. Testing Pyramid
- Unit tests services and mappers in isolation with mocked ports.
- Integration tests.
- E2E once a frontend exists, for example by using Playwright/Cypress for browser-level end-to-end tests.

### 5. CI/CD Pipeline
Setting up a GitHub Actions/Gitlab CI pipeline for automated building, testing, linting and pushing to staging and production environment.

### 6. Staging & Production Environments
Maintain two environments so new features can be validated before reaching end users.

### 7. User Behaviour Tracking
Integrate a product analytics tool to track how users interact with the scheduler.

### 8. Distributed Tracing
Adding tracing so that one can follow a single request across service boundaries and/or identify slow DB queries.
