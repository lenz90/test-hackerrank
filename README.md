# Java 17 + Spring Boot (Maven) para GitHub Codespaces

Proyecto mínimo para correr una app con:

- Java 17
- Maven (`pom.xml`)
- Spring Boot Web
- Lombok
- Mockito (tests)

## Ejecutar en local o Codespaces

```bash
mvn spring-boot:run
```

Luego abre:

```text
http://localhost:8080/greet
http://localhost:8080/greet?name=Ana
```

## Correr tests

```bash
mvn test
```

## Estructura principal

- `pom.xml` dependencias y build Maven
- `src/main/java/com/example/demo/DemoApplication.java` entrada Spring Boot
- `src/main/java/com/example/demo/GreetingController.java` endpoint `/greet`
- `src/main/java/com/example/demo/GreetingService.java` lógica de saludo
- `src/main/java/com/example/demo/GreetingResponse.java` DTO con Lombok
- `src/test/java/com/example/demo/*` tests con JUnit + Mockito

## Dev Container

Ya está incluido en `.devcontainer/` para abrir directamente en GitHub Codespaces con Java 17 y extensiones Java/Copilot.
