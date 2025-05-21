# 📚 Course Management System

A robust backend application developed using **Java 21** and **Spring Boot**, designed to manage courses, students, and enrollments efficiently. The system leverages **PostgreSQL** for data persistence and is fully containerized using **Docker** and orchestrated with **Docker Compose**. Continuous Integration and Deployment are facilitated through a well-defined **Jenkins** pipeline.

---

## 🚀 Features

- **Course Management**: Create, read, update, and delete courses.
- **Student Management**: Handle student records with full CRUD operations.
- **Enrollment Handling**: Enroll students into courses seamlessly.
- **RESTful APIs**: Expose endpoints for all operations.
- **API Documentation**: Integrated Swagger UI for interactive API exploration.
- **Containerization**: Dockerized application for consistent environments.
- **CI/CD Pipeline**: Automated build and deployment using Jenkins.
- **Environment Configuration**: Manage settings via `.env` file.

---

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Containerization**: Docker, Docker Compose
- **CI/CD**: Jenkins
- **API Documentation**: Swagger (OpenAPI)

---

## 📁 Project Structure
Course-Managment-System/
├── .mvn/ # Maven Wrapper
├── src/ # Source code
│ └── main/
│ ├── java/ # Java packages
│ └── resources/ # Application properties
├── .env # Environment variables
├── Dockerfile # Docker configuration
├── docker-compose.yml # Docker Compose setup
├── Jenkinsfile # Jenkins pipeline
---

## ⚙️ Getting Started

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose
- PostgreSQL

### Clone the Repository

```bash
git clone https://github.com/im-faix/Course-Managment-System.git
cd Course-Managment-System
Configure Environment Variables

Create a .env file in the root directory with the following content:
POSTGRES_DB=course_db
POSTGRES_USER=admin
POSTGRES_PASSWORD=secret
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/course_db

Build and Run with Docker Compose
docker-compose up --build

This command will:

    Build the Spring Boot application.

    Set up the PostgreSQL database.

    Start both services in Docker containers.

📑 API Documentation

Once the application is running, access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

This interface allows you to explore and test the available API endpoints interactively.
🔁 CI/CD Pipeline with Jenkins

The project includes a Jenkinsfile that defines the CI/CD pipeline:

    Checkout: Clone the repository.

    Build: Compile the application using Maven.

    Test: Execute unit and integration tests.

    Dockerize: Build Docker images for the application.

    Deploy: Deploy the application to the desired environment.

To set up Jenkins:

    Install Jenkins on your server.

    Configure necessary plugins (e.g., Maven, Docker).

    Create a new pipeline job and link it to this repository.

    Set up credentials and environment variables as needed.

├── pom.xml # Maven configuration
└── README.md # Project documentation

🧰 Useful Commands

    Start Services: docker-compose up --build

    Stop Services: docker-compose down

    View Logs: docker-compose logs -f

    Rebuild Images: docker-compose build --no-cache

🤝 Contributions

Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

