# 🎗️ Charity Inventory Management System - Backend

[![Java Version](https://img.shields.io/badge/Java-17-orange.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen.svg?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security Version](https://img.shields.io/badge/Spring%20Security-6.2-blue.svg?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Database](https://img.shields.io/badge/MySQL-8.0+-blue.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Build Tool](https://img.shields.io/badge/Maven-3.x-red.svg?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

Welcome to the backend repository for the **Charity Inventory Management System**. This backend service is built using Spring Boot, providing a secure, robust, and scalable API for managing users, inventory, and operations for charity programs.

It features complete role-based authentication and authorization using **JSON Web Tokens (JWT)** and **Spring Security**, protecting endpoints and ensuring that only authorized personnel can access or modify inventory records.

---

## 🚀 Key Features

*   **Secure Authentication**: User registration and login utilizing Spring Security and JWT.
*   **Role-Based Access Control (RBAC)**: Support for three distinct user roles:
    *   `ADMIN` (Full administrative rights)
    *   `INVENTORY_STAFF` (Staff managing inventory, items, and requests)
    *   `VOLUNTEER` (Access to basic features, distribution, and tasks)
*   **Token-Based Authorization**: Secure stateless sessions where every sensitive request requires a valid JWT in the `Authorization` header.
*   **Password Security**: Safe password storage using the standard BCrypt hashing algorithm.
*   **Relational Database Mapping**: Database models mapped via JPA (Hibernate) to a MySQL database with automatic table schema creation (`ddl-auto=update`).

---

## 🛠️ Tech Stack & Dependencies

*   **Language**: Java 17
*   **Framework**: Spring Boot 3.2.5 (Spring MVC, Spring Data JPA, Spring Security)
*   **Database**: MySQL
*   **Security & Auth**: JJWT (Java JWT) `0.11.5`, BCrypt
*   **Utilities**: Lombok (eliminates boilerplate code like getters, setters, and builders)
*   **Build System**: Maven

---

## 📋 Prerequisites

Before setting up the project, make sure you have the following installed on your system:

1.  **Java Development Kit (JDK) 17**: Ensure Java is installed and configured in your environment path. Check by running:
    ```bash
    java -version
    ```
2.  **MySQL Server (8.0+)**: Installed and running locally.
3.  **Git**: For cloning the repository.
4.  **IDE (Optional but Recommended)**: IntelliJ IDEA (Community or Ultimate), Eclipse, or Visual Studio Code.

---

## 💻 Local Setup Instructions

Follow these simple steps to set up and run the backend on your computer:

### Step 1: Clone the Repository
Clone this repository to your local machine using Git:
```bash
git clone https://github.com/PawanBulathwaththa/Charity-Inventory-Management-System-Backend.git
cd Charity-Inventory-Management-System-Backend
```

### Step 2: Set Up the Database
1.  Open your MySQL Command Line Client, MySQL Workbench, or your preferred database tool.
2.  Log in to your local MySQL instance.
3.  Create a new database named `charity_db`:
    ```sql
    CREATE DATABASE charity_db;
    ```

### Step 3: Configure Database Settings
Navigate to the project directory and open the configuration file:
`src/main/resources/application.properties`

Update the database credentials to match your local MySQL configuration:
```properties
# Database URL (points to your localhost and charity_db)
spring.datasource.url=jdbc:mysql://localhost:3306/charity_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

# Database Credentials - Update these to match your MySQL setup
spring.datasource.username=YOUR_MYSQL_USERNAME (usually "root")
spring.datasource.password=YOUR_MYSQL_PASSWORD (e.g., "password123", or leave empty if no password)
```

> [!NOTE]
> The property `spring.jpa.hibernate.ddl-auto=update` is active. This means Hibernate will automatically generate all the necessary tables (`users`, etc.) inside the `charity_db` database as soon as the application runs for the first time.

### Step 4: Run the Application
You can run the application directly from your terminal/command prompt using the Maven Wrapper bundled in the project:

#### For Windows:
```cmd
mvnw.cmd clean spring-boot:run
```

#### For macOS & Linux:
```bash
chmod +x mvnw
./mvnw clean spring-boot:run
```

Alternatively, you can import the project into your IDE (IntelliJ, Eclipse, etc.) as a **Maven Project** and run the main class `CharityManagementBackApplication.java` directly.

The server will start up on port **8080** by default:
`http://localhost:8080`

---

## 🔌 API Endpoints (Authentication)

The backend provides the following authentication endpoints out-of-the-box:

### 1. User Registration
*   **Endpoint**: `POST /api/v1/auth/register`
*   **Access**: Public
*   **Request Body** (JSON):
    ```json
    {
      "name": "Jane Doe",
      "email": "jane@example.com",
      "password": "securepassword123",
      "role": "INVENTORY_STAFF"
    }
    ```
    *Available roles:* `ADMIN`, `INVENTORY_STAFF`, `VOLUNTEER`
*   **Response** (JSON - `200 OK`):
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5lQGV4YW1wbGUuY29tIiwiaWF0IjoxNzg0ODU2OTI0LCJleHAiOjE3ODQ5NDMzMjR9..."
    }
    ```

### 2. User Login
*   **Endpoint**: `POST /api/v1/auth/login`
*   **Access**: Public
*   **Request Body** (JSON):
    ```json
    {
      "email": "jane@example.com",
      "password": "securepassword123"
    }
    ```
*   **Response** (JSON - `200 OK`):
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYW5lQGV4YW1wbGUuY29tIiwiaWF0IjoxNzg0ODU2OTI0LCJleHAiOjE3ODQ5NDMzMjR9..."
    }
    ```

---

## 🔒 Accessing Secured Endpoints
When you develop future endpoints or access any endpoint that requires authentication (e.g. any URL other than `/api/v1/auth/**`), you must attach the JWT token retrieved from register/login to your HTTP requests:

*   **Header Name**: `Authorization`
*   **Header Value**: `Bearer <YOUR_JWT_TOKEN>`

---

## 📁 Directory Structure

```text
Charity-Inventory-Management-System-Backend/
├── .mvn/                         # Maven wrapper files
├── src/
│   ├── main/
│   │   ├── java/com/charitymanagement/api/charitymanagementback/
│   │   │   ├── auth/             # Core authentication feature group
│   │   │   │   ├── controller/   # Rest Controllers & Exception Handlers
│   │   │   │   ├── dto/          # Data Transfer Objects (Requests & Responses)
│   │   │   │   ├── entity/       # JPA Entities (User, Role enum)
│   │   │   │   ├── repository/   # Spring Data JPA Repositories
│   │   │   │   └── service/      # Business logic (Auth, JWT signing/verifying)
│   │   │   ├── config/           # Security configuration, CORS, filter bindings
│   │   │   └── CharityManagementBackApplication.java   # App Entry Point
│   │   └── resources/
│   │       └── application.properties  # Database & JWT properties configuration
│   └── test/                     # JUnit and Integration Tests
├── pom.xml                       # Maven dependency management
└── README.md                     # Project documentation
```

---

## 🛠️ Security Settings (JWT Key customization)
Inside `application.properties`:
*   `application.security.jwt.secret-key` is configured with a 256-bit hexadecimal key. You can generate a new secure custom 256-bit key for production environments.
*   `application.security.jwt.expiration` is configured to `86400000` (24 hours in milliseconds). Adjust this as needed for your session length.
