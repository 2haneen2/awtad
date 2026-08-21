# Awtad Backend

The Spring Boot REST API for Awtad, a cooperative gamified application that helps two companions stay consistent with their shared daily spiritual routine.

## Current status

The backend foundation is operational:

- Spring Boot application starts successfully.
- PostgreSQL development and test databases are separated.
- Flyway database migrations are enabled.
- Actuator health checks are available.
- The application context test runs against the test database.

## Technology stack

- Java 21
- Spring Boot 4.1.1
- Maven Wrapper
- Spring Web MVC
- Spring Data JPA
- PostgreSQL 17
- Flyway
- Bean Validation
- Spring Boot Actuator
- JUnit 5

## Local databases

| Database | Purpose |
|---|---|
| `awtad_dev` | Local development |
| `awtad_test` | Automated tests |

Both databases are owned locally by the restricted application role `awtad_app`.

## Environment variables

The following values must be configured locally and must never be committed:

| Variable | Required | Purpose |
|---|---|---|
| `DB_PASSWORD` | Yes | Development database password |
| `DB_URL` | No | Overrides the development database URL |
| `DB_USERNAME` | No | Overrides the development database user |
| `SERVER_PORT` | No | Overrides the default port `8080` |
| `TEST_DB_PASSWORD` | Yes for tests | Test database password |
| `TEST_DB_URL` | No | Overrides the test database URL |
| `TEST_DB_USERNAME` | No | Overrides the test database user |

Configure secrets using local IntelliJ run configurations with **Store as project file** disabled.

## Running locally

1. Start PostgreSQL.
2. Configure `DB_PASSWORD` in the Spring Boot run configuration.
3. Run `AwtadBackendApplication`.
4. Verify the service:

```http
GET http://localhost:8080/actuator/health