# Java 17 + Spring Boot (Maven) para GitHub Codespaces

Este repo ya incluye lo mínimo para crear y correr una app con:

- Java 17
- Maven (`pom.xml`)
- Spring Boot Web
- Lombok
- Mockito (tests)

## Ejecutar

```bash
mvn spring-boot:run
```

Endpoint de prueba:

- `http://localhost:8080/greet`
- `http://localhost:8080/greet?name=Ana`

## Test

```bash
mvn test
```

## Archivos clave

- `.devcontainer/devcontainer.json` configuración Codespaces (Java 17 + Copilot)
- `.vscode/launch.json` configuración Run para `DemoApplication`
- `pom.xml` dependencias Maven
- `src/main/java/com/example/demo/*` app mínima Spring Boot
- `src/test/java/com/example/demo/*` pruebas con JUnit + Mockito

## Scheduling system (OOP)

Se agregó un módulo de scheduling en `com.example.demo.scheduling` con:

- `Attendee`, `Interviewer`, `Room`, `TimeSlot`, `Interview`
- `ScheduleService` con `scheduleInterviews(...)` y resultado de no agendados
- `ScheduleServiceTest` con casos de capacidad máxima y validación de no solapamientos
