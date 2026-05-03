# Tool-96 — Patch Compliance Reporter

> **Capstone Project** | Sprint: Mon 14 Apr – Fri 9 May 2026 | Demo Day: 9 May 2026  
> Team: 5 Members | Java Developer 2: Sagar M D

---

## What This Project Does

An AI-powered web application that tracks software patch compliance across IT assets. It generates AI descriptions and recommendations using Groq (LLaMA-3), displays compliance dashboards, and helps security teams manage patch deadlines.

---

## Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| Backend | Java 17, Spring Boot 3.x | REST API, business logic |
| Database | PostgreSQL 15 | Primary data store |
| Cache | Redis 7 | Response caching |
| Migrations | Flyway | Schema version control |
| Security | Spring Security + JWT | Authentication |
| AI Service | Python 3.11, Flask, Groq | AI descriptions |
| Frontend | React 18 + Vite | User interface |
| Styling | Tailwind CSS | Responsive design |
| HTTP Client | Axios | API calls |
| Charts | Recharts | Analytics dashboards |
| Container | Docker + Docker Compose | Full-stack deployment |
| Docs | Swagger / OpenAPI | API documentation |

---

## Getting Started

### Prerequisites
- Docker Desktop installed and running
- Java 17 (from adoptium.net)
- Node 18+
- Python 3.11

### 1. Clone the repository
```bash
git clone https://github.com/sagar9945/patch-compliance-reporter.git
cd patch-compliance-reporter
```

### 2. Set up environment variables
```bash
cp .env.example .env
# Edit .env — add your GROQ_API_KEY
```

### 3. Run everything with Docker Compose
```bash
docker-compose up --build
```

| Service | URL |
|---|---|
| Frontend | http://localhost |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| AI Service | http://localhost:5000 |

### 4. Run locally (without Docker)

**Backend:**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm run dev
# http://localhost:3000
```

---

## Project Structure
patch-compliance-reporter/
├── backend/
│   └── src/main/java/com/internship/tool/
│       ├── controller/     # REST endpoints with Swagger docs
│       ├── service/        # Business logic
│       ├── repository/     # JPA data access
│       ├── entity/         # Database models
│       ├── config/         # Security, Redis, Swagger, AOP, Seeder
│       └── exception/      # Custom exceptions
│   └── src/main/resources/
│       └── db/migration/   # V1, V2, V3 Flyway SQL files
├── ai-service/             # Flask Python microservice
├── frontend/
│   └── src/
│       ├── components/     # Shared Navbar
│       ├── context/        # AuthContext
│       ├── hooks/          # useWindowSize
│       ├── pages/          # All page components
│       └── services/       # Axios API calls
├── docker-compose.yml
├── .env.example
└── README.md

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/patch-records | Get all records (paginated) |
| GET | /api/patch-records/{id} | Get record by ID |
| POST | /api/patch-records | Create new record |
| PUT | /api/patch-records/{id} | Update record |
| DELETE | /api/patch-records/{id} | Soft delete |
| GET | /api/patch-records/search | Search records |
| GET | /api/patch-records/stats | Dashboard statistics |
| GET | /api/patch-records/export | Export as CSV |
| POST | /api/patch-records/upload | Upload file (PDF/PNG/JPG, max 5MB) |
| POST | /api/auth/login | Login — returns JWT |
| POST | /api/auth/register | Register new user |

Full interactive docs: **http://localhost:8080/swagger-ui.html**

---

## Database Migrations

| File | What it does |
|---|---|
| `V1__init.sql` | Creates patch_records, users, audit_log tables |
| `V2__add_audit_log.sql` | Adds audit trigger, JSONB columns |
| `V3__performance_indexes.sql` | Composite + full-text indexes |

---

## Frontend Pages

| Page | Route | Description |
|---|---|---|
| Login | /login | JWT authentication |
| Records | / | List with search, filter, export |
| Dashboard | /dashboard | KPI cards + severity chart |
| Analytics | /analytics | 4 Recharts charts + period selector |
| Detail | /patch/:id | Full record + AI panel |
| Create/Edit | /patch/new, /patch/:id/edit | Form with validation |

---

## Security Features

- JWT Bearer token authentication
- BCrypt password hashing
- Spring Security RBAC
- Input validation on all endpoints
- SQL injection protection via JPA
- File upload validation (type + size)
- Audit logging via Spring AOP

---

## Daily Git Log

| Day | Commit | What Was Built |
|---|---|---|
| Day 1 | Folder structure, V1 migration, pom.xml, docker-compose | Foundation |
| Day 2 | React + Vite, Axios, Tailwind, Login page | Frontend setup |
| Day 3 | V2 audit migration, PatchListPage with badges | List page |
| Day 4 | Create/Edit form with validation, search bar | CRUD forms |
| Day 5 | AuthContext, ProtectedRoute, Navbar | Authentication |
| Day 6 | Dashboard KPI cards, Recharts, Detail page | Dashboard |
| Day 7 | Debounced search, filters, AuditAspect AOP | Filters + AOP |
| Day 8 | MockMvc tests, AI panel with spinner | Tests + AI UI |
| Day 9 | Swagger config, CSV export, file upload | API docs |
| Day 10 | DataSeeder 15 records, Analytics page | Data + Charts |
| Day 11 | Responsive design 375/768/1280px | Mobile support |
| Day 12 | Code review, standards, release v1.0 | Final cleanup |
| Day 13 | Demo video, demo script, demo mode data | Recorded |
| Day 14 | Loading skeleton, 404 page, 3 presentation slides | Polish |
| Day 15 | Full backend wired, entity/repo/service, data seeder | Backend |
| Day 16 | Demo scenarios, portfolio screenshots | Prep |
| Day 17 | SECURITY.md draft, rehearsal checklist, Q&A prep | Security |
| Day 18 | Final SECURITY.md, code audit, feature verification | Final |

---

## Developer

**Java Developer 2 — Sagar M D**  
GitHub: [@sagar9945](https://github.com/sagar9945)