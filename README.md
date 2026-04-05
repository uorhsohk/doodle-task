# doodle-task

## How to run

First, start the database. You'll need Docker installed — then just run:

```
docker-compose up -d
```

That spins up a Postgres instance on port 5432. Once it's up, start the app:

```
./gradlew bootRun
```

The service will be available at http://localhost:8080.

To stop everything, hit `Ctrl+C` to stop the app, then bring down the database with:

```
docker-compose down
```

You can explore the API interactively at http://localhost:8080/swagger-ui/index.html.
