# Food Bazar

Food Bazar is a food delivery management application built with Spring Boot, Thymeleaf, and SQLite. The project includes role-based signup and login for users, restaurants, and delivery personnel, along with food management, ordering, and delivery workflows.

## Key Features

- Signup and login for user, restaurant, and delivery accounts
- Restaurant food item creation and management
- Food browsing and order flow for customers
- Delivery requests sent by restaurants
- Available and active delivery dashboards
- Delivery acceptance and completion workflow
- Thymeleaf-based web interface
- SQLite database access through JPA and Hibernate
- Image uploads stored in and served from the local filesystem

## Technology Stack

- Java 25
- Spring Boot 3.5.7
- Spring MVC
- Spring Data JPA / Hibernate
- SQLite 3
- Thymeleaf
- Maven
- Lombok

## Requirements

- JDK 25
- Maven, or the included Maven Wrapper

Verify your Java installation:

```powershell
java -version
```

## Running the Application

Open a terminal in the project root directory and run:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, use Maven:

```powershell
mvn spring-boot:run
```

After the application starts, open:

```text
http://localhost:8080
```

## Database

The SQLite database configuration is defined in [application.properties](src/main/resources/application.properties).

```properties
spring.datasource.url=jdbc:sqlite:src/main/resources/database/app.db
```

Database file:

```text
src/main/resources/database/app.db
```

The `spring.jpa.hibernate.ddl-auto=update` setting updates the database schema based on the application entities at runtime.

## File Upload

Uploaded images are stored in the [uploads](uploads/) directory and served as public static resources. The maximum size for a single file upload is 10 MB.

## Important Routes

| Route | Description |
| --- | --- |
| `/` | Home page |
| `/signup` | Account signup |
| `/login` | Login |
| `/user/dashboard` | User dashboard |
| `/user/orders` | User order list |
| `/restaurant/dashboard` | Restaurant dashboard |
| `/restaurant/food/add` | Add a new food item |
| `/restaurant/foods` | Restaurant food list |
| `/restaurant/orders` | Restaurant orders |
| `/delivery/dashboard` | Delivery dashboard |

## Project Structure

```text
src/
├── main/
│   ├── java/com/example/SQLite/demo/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── resources/
│       ├── database/app.db
│       ├── static/
│       ├── templates/
│       └── application.properties
└── test/
```

## Build and Test

Build the project:

```powershell
.\mvnw.cmd clean package
```

Run the tests:

```powershell
.\mvnw.cmd test
```

## IDE setup

Open the project folder in VS Code or IntelliJ IDEA. The Java extension in VS Code or IntelliJ IDEA can detect and configure JDK 25 for the project.
