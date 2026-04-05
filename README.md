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
