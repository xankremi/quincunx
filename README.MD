# Quincunx Bank Platform

Modular microservice-based platform using **DDD, CQRS, and Event Sourcing** principles.

---

## 📁 Project Structure

```
quincunx/
├─ docs/            # Documentation (architecture & style guides)
├─ infrastructure   # Infrastructure (Local dev environment and etc.)
├─ libs/            # Common libraries shared across services
└─ services/        # Microservices
```

## 📖 Documentation

- **[Architecture](docs/architecture.md)** – System architecture and command/event flows
- **[Libraries Style Guide](docs/libraries-style-guide.md)** – Cross-service VO, events, commands, DTOs
- **[Services Style Guide](docs/services-style-guide.md)** – Service layer structure and DDD conventions

---

## 🏗️ Local Development

```bash
cd infrastructure/docker/dev
docker-compose up -d
```

See `infrastructure/docker/dev/README.md` for environment-specific instructions.

---

## ⚙️ Recommended Workflow

- Follow style guides when creating new services.
- Use Value Objects (VOs) for domain concepts.
- Keep ACID transactional flows separate from async/batch paths.
- Update documentation whenever new services or libraries are added.