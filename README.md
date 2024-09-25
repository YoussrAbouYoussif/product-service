# Products Service
This is a **RESTful Product Service** built using **Java 17** and **Spring Boot**. The application provides CRUD operations for managing products, with **H2** as the database, **Swagger** for API documentation, and **Spring Security** for authentication and authorization.

## Features
- **CRUD Operations**: Create, Read, Update, Delete products.
- **H2 Database**: File-based H2 database for lightweight persistence.
- **Swagger API Documentation**: Explore and test the API endpoints.
- **Spring Security**: Basic HTTP authentication for securing endpoints.
- **Validation**: Input validation for requests.

## Technologies Used
- **Java 17**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **H2 Database**
- **Spring Security**
- **Swagger (Springdoc OpenAPI)**
- **Maven** (for build automation)

## Getting Started

### Prerequisites

- **Java 17**: Make sure you have JDK 17 installed.
- **Maven**: Ensure you have Maven installed and configured.


## Setup Instructions

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/product-crud-api.git
    cd product-service
    ```
2. **Build the project:**

    ```bash
    mvn clean install
    ```
3. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```
4. The application will start at [http://localhost:8080]

### Running Tests

You can run unit and integration tests using Maven:

```bash
mvn test
```

## Swagger API Documentation

The application provides **Swagger API documentation** Swagger provides an interactive interface where you can explore and test the API endpoints. You can access it by navigating to:

```bash
http://localhost:8080/swagger-ui/index.html
```

### API Endpoints

The following endpoints are available:

- **POST /api/products**: Create a new product.
- **GET /api/products/{id}**: Retrieve a product by ID.
- **GET /api/products**: Retrieve all products.
- **PUT /api/products/{id}**: Update a product.
- **DELETE /api/products/{id}**: Delete a product by ID.

## Spring Security

The application is secured using **Spring Security** with basic HTTP authentication.

### Default Credentials

For development purposes, an in-memory user is set up with the following credentials:

- **Username**: `admin`
- **Password**: `password`
- **Role**: `ADMIN`