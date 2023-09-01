# Spring Boot Account Management Application

This repository contains a Spring Boot application for account management. It includes functionality to deposit, withdraw, and check the balance of accounts.

## Getting Started

### Running Locally

To run the application locally, follow these steps:

1. Make sure you have Java (JDK 8 or higher) and Maven installed on your machine.

The application will be accessible at `http://localhost:8080`.

### Running with Docker

To run the application using Docker, follow these steps:

1. Make sure you have Docker installed on your machine.
2. Build the application: `mvn clean package`
3. Open the terminal and run: `docker-compose up --build`

The application will be accessible at `http://localhost:8080`.

### Accessing H2 Database Console

The H2 Database Console is accessible for viewing and managing the in-memory database used by the application. To access the H2 Database Console, follow these steps:

1. Start the application by running the JAR file as mentioned above.
2. Open your web browser and go to: `http://localhost:8080/h2-console`
3. In the H2 Database Console login page, enter the following:
    - JDBC URL: `jdbc:h2:mem:kata-db`
    - User Name: `sa`
    - Password: (Leave it empty)
4. Click the "Connect" button to access the H2 Database Console.

## API Endpoints

- Deposit money: `POST /Account/deposit/{accountId}?amount={amount}`
- Withdraw money: `POST /Account/withdraw/{accountId}?amount={amount}`
- Get current balance: `GET /Account/balance/{accountId}`
- Get transactions: `GET /Account/transactions/{accountId}"`

Replace `{accountId}` and `{amount}` with appropriate values.

## Default Account

An account is already created with the following values:
- ID: 1
- FirstName: Hassan
- LastName: Benharouga
- Balance: 100000
- Transactions: Null

### Accessing Swagger API Documentation

Swagger is integrated into the application to provide interactive API documentation. To access the Swagger UI and explore the API endpoints, follow these steps:

1. Start the application using the steps mentioned above.
2. Open your web browser and go to: `http://localhost:8080/swagger-ui/index.html`
3. Use the Swagger UI to browse the available endpoints, send requests, and view responses.

Please note that the Swagger API documentation provides a comprehensive overview of the available endpoints and their functionalities.

