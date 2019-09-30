# Introduction

This is a Spring Boot demo application that configures 2 different MySQL data sources with Hikari pool

# Requirements

- Java 8+
- Maven
- Docker + Docker Compose

# Usage

Start 2 MySQL servers using docker compose:
```bash
docker-compose up
```

Build and run tests:
```bash
mvn test
```

Stop the MySQL servers:
```bash
docker-compose stop
```