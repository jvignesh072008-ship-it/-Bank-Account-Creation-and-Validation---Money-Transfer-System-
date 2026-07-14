# Bank-Account-Creation-and-Validation---Money-Transfer-System

# Bank Application 

A simple Spring Boot REST API for managing bank accounts and money transfers between accounts.

## Tech Stack

- **Java 26**
- **Spring Boot 3.9.16**
- **Maven** (build tool)
- **H2 Database** (in-memory) — *dependency present, not yet wired into the data layer (see [Known Limitations](#known-limitations))*

## Features

- Create a new bank account
- Fetch account details by account number
- List all accounts
- Transfer money between two accounts (with PIN validation)
- List all transactions
- Centralized exception handling with meaningful error messages (invalid account, insufficient balance, invalid PIN, same-account transfer, etc.)

## Project Structure

```
src/main/java/com/bank/
├── BankApplication.java          # Main entry point
├── controller/
│   ├── BankAccountController.java   # /api/accounts endpoints
│   └── TransactionController.java   # /api/transactions endpoints
├── dto/
│   ├── BankAccountRequest.java
│   └── TransactionRequest.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ...custom exceptions (InsufficientBalance, InvalidPin, etc.)
├── model/
│   ├── BankAccount.java
│   └── Transaction.java
├── repository/
│   ├── BankAccountRepository.java
│   └── TransactionRepository.java
└── service/
    ├── BankAccountService.java
    └── TransactionService.java
```

## Prerequisites

- JDK 21 or later installed (`java -version`)
- Maven installed and on your PATH (`mvn -version`)

## Getting Started

1. **Clone or download the project**, then navigate into it:
   ```bash
   cd BankApplicationV3
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. The app starts on:
   ```
   http://localhost:8082
   ```

## API Endpoints

### Accounts — `/api/accounts`

| Method | Endpoint                        | Description                  |
|--------|----------------------------------|-------------------------------|
| POST   | `/api/accounts`                 | Create a new bank account     |
| GET    | `/api/accounts/{accountNumber}` | Get account details by number |
| GET    | `/api/accounts`                 | List all accounts             |

**Create account — request body:**
```json
{
  "accountNumber": "ACC1001",
  "customerName": "Ravi Kumar",
  "mobileNumber": "9876543210",
  "email": "ravi@example.com",
  "initialDeposit": 5000.0,
  "accountType": "SAVINGS"
}
```

### Transactions — `/api/transactions`

| Method | Endpoint                     | Description                    |
|--------|-------------------------------|---------------------------------|
| POST   | `/api/transactions/transfer` | Transfer money between accounts |
| GET    | `/api/transactions`          | List all transactions           |

**Transfer — request body:**
```json
{
  "senderAccountNumber": "ACC1001",
  "receiverAccountNumber": "ACC1002",
  "transferAmount": 1500.0,
  "transactionPin": "1234"
}
```

## Configuration

Key settings in `src/main/resources/application.properties`:

```properties
server.port=8082

spring.datasource.url=jdbc:h2:mem:bankdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### H2 Console

With the app running, open:
```
http://localhost:8082/h2-console
```
Login with:
- **JDBC URL:** `jdbc:h2:mem:bankdb`
- **User Name:** `sa`
- **Password:** *(leave blank)*

## Known Limitations

- **Data is in-memory only.** `BankAccount` and `Transaction` are currently plain POJOs (no `@Entity` annotations), and the repositories store data in `LinkedHashMap` collections rather than using Spring Data JPA. This means:
  - All data is lost on every application restart.
  - No tables currently appear in the H2 console, even though H2/JPA dependencies and console config are present.
  - To persist data properly, the models need `@Entity`/`@Id` annotations and the repositories need to extend `JpaRepository`.

## License

Internal / educational project — no license specified.
