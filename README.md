# 🧩 Containerized User API

A Spring Boot REST API backed by PostgreSQL, with full support for:

- ✅ Testcontainers-powered integration testing using a reusable container toolkit
- ✅ Docker Compose-based black-box testing for end-to-end flows

---

## 🚀 Running Locally with Docker Compose

To run the app along with PostgreSQL via Docker Compose:

```bash
docker-compose up --build


Then call the API:

```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"Alice"}'
```

## 🧪 Run Integration Tests

```bash
mvn test
```

These tests use Testcontainers toolkit from [containers-toolkit](https://github.com/asrufmo/containers-toolkit).

## 🧰 Tech Stack
🟦 Java 17

🟩 Spring Boot

🐘 PostgreSQL

🧪 Testcontainers

🧱 Maven + JUnit 5

🧰 Custom container toolkit for reusable orchestration

## 📦 Toolkit Dependency

```xml
<dependency>
  <groupId>com.testing</groupId>
  <artifactId>containers-toolkit</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>test</scope>
</dependency>
```

## 📁 Project Structure

```
.
├── src/main/java          # Spring Boot app (UserController, User model, etc.)
├── src/test/java          # Integration tests using Testcontainers
├── Dockerfile             # App container definition
├── docker-compose.yml     # App + DB for local testing
└── README.md

```

## 📄 License

MIT# containerized-user-api
