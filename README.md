# Containerized User API

Spring Boot REST API with full support for:

- ✅ Testcontainers-based integration tests
- ✅ Docker Compose-based black-box testing

## 🚀 Running Locally with Docker Compose

```bash
docker-compose up --build
```

Then call the API:

```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"Alice"}'
```

## 🧪 Run Integration Tests

```bash
mvn test
```

These tests use Testcontainers toolkit from [containers-toolkit](https://github.com/asrufmo/containers-toolkit).

## 🧰 Stack

- Java 17
- Spring Boot
- PostgreSQL (via Docker or Testcontainers)
- Maven
- JUnit 5

## 📦 Toolkit Dependency

```xml
<dependency>
  <groupId>com.testing</groupId>
  <artifactId>containers-toolkit</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>test</scope>
</dependency>
```

## 📄 License

MIT# containerized-user-api
