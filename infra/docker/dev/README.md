# Local Development Environment for QUINCUNX Platform

This directory contains a **single Docker Compose file** for running a full local development environment for the Event Sourcing-based QUINCUNX Platform.

## 🚀 Features

- **PostgreSQL**: Event Store, Inbox/Outbox
- **MongoDB**: Document-based projections (read models)
- **Elasticsearch**: Search/analytics projections
- **Kafka + Zookeeper**: Command/Event transport between microservices
- **Kibana**: Elasticsearch visualization
- **Kafka UI**: Monitor Kafka topics, producers, and consumers
- **Prometheus**: Metrics collection
- **Grafana**: Metrics visualization dashboards
- **pgAdmin**: GUI for PostgreSQL
- **mongo-express**: GUI for MongoDB

## 📂 File Structure

```
docker/dev/
├─ docker-compose.yml          # Combined infrastructure + tools + exporters + GUI
├─ prometheus.yml              # Prometheus configuration for metrics
├─ .env                        # Environment variables (ports, credentials)
└─ README.md                   # This file
```

## ⚡ Usage

### 1. Start full local development environment

```bash
docker-compose --env-file docker/dev/.env up -d
```

### 2. Stop all services

```bash
docker-compose --env-file docker/dev/.env down
```

---

## 🌐 UI Access

| Service           | URL                        | Notes |
|------------------|----------------------------|-------|
| Kafka UI          | http://localhost:8080      | Monitor Kafka topics, producers, consumers |
| Kibana            | http://localhost:5601      | Elasticsearch visualization |
| Prometheus        | http://localhost:9090      | Metrics collection |
| Grafana           | http://localhost:3000      | Default: admin/admin |
| pgAdmin           | http://localhost:5050      | Default: admin@admin.com / admin |
| mongo-express     | http://localhost:8082      | MongoDB GUI |

---

## 📝 Notes

- This environment is **intended for local development only**, not for production.
- All ports and credentials are configured in `.env`.
- Prometheus scrapes metrics from infrastructure and microservices (via Spring Boot `/actuator/prometheus`).
- pgAdmin and mongo-express provide GUI access to PostgreSQL and MongoDB.
- Grafana dashboards can visualize metrics from all services.
- Dependencies are handled via `depends_on`, all services are in a single `docker-compose.yml` for simplicity.

---

## 🔹 PostgreSQL & MongoDB Connections

### PostgreSQL (pgAdmin)
- **Server Name:** Bank DB
- **Host:** `postgres`
- **Port:** `5432`
- **Username:** `${POSTGRES_USER}`
- **Password:** `${POSTGRES_PASSWORD}`
- **Maintenance DB:** `${POSTGRES_DB}`

### MongoDB (mongo-express)
- **Server:** `mongo`
- **Port:** `27017`
- **Authentication:** None (dev environment)
- Access databases: `payment`, `notifications` (create if needed)

---

## ⚡ Tips

- Volumes persist data across restarts.
- Use `.env` to adjust ports and credentials.
- Start/stop the environment with simple `docker-compose` commands.
- GUI tools allow quick inspection of data, topics, and metrics.  
