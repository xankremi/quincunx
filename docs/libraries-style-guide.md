# Common Libraries Style Guide

This guide describes **how to structure and maintain common libraries** (`libs/core`, `libs/infrastructure`, `libs/interfaces`) that are shared between microservices.  
All developers should follow this guide to ensure consistency and proper DDD practices.

---

## Project Structure

The recommended structure for common libraries:

```
libs/
├─ core/
│ ├─ domain/ # Cross-service Domain Events, Value Objects
│ ├─ application/ # Cross-service Commands, Queries, DTOs
│
├─ infrastructure/
│ └─ mongo/ # Mongo converters, serializers, helpers
│
└─ interfaces/ # Abstract interfaces for Messaging & API contracts
```

- **core/domain**: domain primitives, shared events
- **core/application**: DTOs, Commands, Queries for cross-service communication
- **infrastructure/mongo**: converters and serializers for projections
- **interfaces**: abstract messaging or API contracts

---

## Layer Responsibilities

### Domain Layer (`core/domain`)
- Shared **Value Objects (VO)**, e.g., `PaymentId`, `Amount`, `Currency`, `UserId`
- Cross-service **Domain Events**
- **No dependencies** on Spring, Axon, Kafka

### Application Layer (`core/application`)
- Cross-service **Commands**, **Queries**, **DTOs**
- Used by multiple microservices to maintain **consistent contracts**
- Immutable objects and proper versioning for events

### Infrastructure Layer (`libs/infrastructure`)
- Technical utilities for projections, messaging, or database helpers
- Example: Mongo converters, Elasticsearch serializers

### Interfaces Layer (`libs/interfaces`)
- Abstract interfaces for messaging or API contracts
- Ensures services depend only on contracts, not implementations

---

## Value Objects (VO)
- VO are **immutable objects** representing domain concepts.
- They encapsulate **validation and domain rules**.
- Use VOs in commands, events, and aggregates instead of primitive types.

---

### Example VOs

```kotlin
data class PaymentId(override val value: String) : StringValue
```

---

## Example Common Command

```kotlin
data class CreatePaymentCommand(
    val paymentId: PaymentId,
    val amount: Amount,
    val currency: Currency,
    val userId: UserId
)
```

---

## Example Common Event

```kotlin
data class PaymentStatusDto(
val paymentId: PaymentId,
val status: PaymentStatus
)
```

---

## Layered Dependency Diagram

```mermaid
flowchart LR
subgraph Libs[Common Libraries]
direction TB
CoreDomain["core/domain\n(Value Objects, Domain Events)"]
CoreApp["core/application\n(Commands, Queries, DTOs)"]
InfraMongo["infrastructure/mongo\n(Mongo converters, serializers)"]
Interfaces["interfaces\n(Messaging & API contracts)"]
end

    subgraph Service[Example Service]
        direction TB
        Domain["Domain Layer"]
        Application["Application Layer"]
        Infra["Infrastructure Layer"]
        InterfacesSvc["Interfaces Layer"]
    end

    Domain --> CoreDomain
    Application --> CoreApp
    Infra --> InfraMongo
    InterfacesSvc --> Interfaces
    Application --> Domain
    Infra --> Domain
    InterfacesSvc --> Application
```

---

## Guidelines for Developers

1. **Follow the folder structure** strictly.
2. **Domain layer** in common libs must remain **framework-agnostic**.
3. **Application layer** contains only cross-service commands, queries, DTOs.
4. **Infrastructure layer** is optional, only for technical helpers.
5. **Interfaces layer** defines abstract contracts; services implement them.
6. **Events, Commands, DTOs** must be immutable. Use versioning when modifying.
7. Keep **local, service-specific events/commands** inside the service, not in common libs.
8. Document flows and dependencies with **Mermaid diagrams** for clarity.

---

## Summary

- Enforces consistent DDD layers in shared libraries.
- Separates cross-service contracts from service-local logic.
- Developers have a clear template for adding new common components.
- Supports integration with Spring Boot, Kotlin, Axon, Kafka, MongoDB, Elasticsearch.