# To-Do App Full-Stack Project

This repository contains the full-stack To-Do App project. The backend is built with Spring Boot (including Spring Security with JWT, Spring Data JPA, and MySQL), and the frontend is built with React, Material-UI (MUI), and React Router.

---

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Backend Setup](#backend-setup)
  - [Technologies Used](#technologies-used-backend)
  - [Prerequisites](#prerequisites-backend)
  - [Configuration](#configuration-backend)
  - [Building and Running](#building-and-running-backend)
  - [API Endpoints](#api-endpoints-backend)
- [Frontend Setup](#frontend-setup)
  - [Technologies Used](#technologies-used-frontend)
  - [Prerequisites](#prerequisites-frontend)
  - [Project Structure](#project-structure)
  - [Running the Frontend](#running-the-frontend)
  - [Application Flow](#application-flow)
- [Troubleshooting](#troubleshooting)
- [Additional Notes](#additional-notes)

---

## Architecture Overview

- **Backend:**  
  A Spring Boot REST API that supports user authentication (registration and login), project management, and task management. JWT is used for stateless security, and MySQL is used as the database.
  
- **Frontend:**  
  A React application that allows users to log in, create and view projects, and manage tasks within projects. Material-UI is used for styling and React Router for navigation.

---

## Backend Setup

### Technologies Used (Backend)

- Java 17
- Spring Boot 3.x
- Spring Security with JWT
- Spring Data JPA
- MySQL
- Maven

### Prerequisites (Backend)

- Java JDK 17 installed
- Maven installed
- MySQL installed and running
- A MySQL database created (e.g., `todo_app`)

### Configuration (Backend)

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/todo-backend.git
   cd todo-backend
   
2. **Configure Database:**
Edit src/main/resources/application.properties:
- spring.datasource.url=jdbc:mysql://localhost:3306/todo_app?useSSL=false&serverTimezone=UTC
- spring.datasource.username=your_mysql_username
- spring.datasource.password=your_mysql_password
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true

3. **Security and CORS:**
      Your security is configured in SecurityConfig.java which includes JWT authentication and a global CORS configuration. Ensure that your CORS configuration allows:
      - Origin: http://localhost:3000
      - Methods: GET, POST, PUT, DELETE, OPTIONS
      - Headers: All (or at least the ones needed such as Authorization)
      - Allow Credentials: true

Building and Running (Backend)

<pre>
todo-backend
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── spring
│   │   │           └── To_Do_App
│   │   │               ├── config
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   └── WebConfig.java
│   │   │               ├── controller
│   │   │               │   ├── AuthController.java
│   │   │               │   ├── ProjectController.java
│   │   │               │   └── UserController.java
│   │   │               ├── model
│   │   │               │   ├── Project.java
│   │   │               │   ├── Task.java
│   │   │               │   ├── User.java
│   │   │               │   └── (other model classes)
│   │   │               ├── repository
│   │   │               │   ├── ProjectRepository.java
│   │   │               │   ├── TaskRepository.java
│   │   │               │   ├── UserRepository.java
│   │   │               │   └── (other repository classes)
│   │   │               ├── security
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   └── JwtUtils.java
│   │   │               └── service
│   │   │                   ├── CustomUserDetailsService.java
│   │   │                   ├── ProjectService.java
│   │   │                   ├── UserService.java
│   │   │                   └── (other service classes)
│   │   └── resources
│   │       ├── application.properties
│   │       └── (other resource files)
├── pom.xml
└── README.md

</pre>
