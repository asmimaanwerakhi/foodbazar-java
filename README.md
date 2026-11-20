# Food Bazar

Spring Boot, Thymeleaf এবং SQLite ব্যবহার করে তৈরি একটি food delivery management application। এই প্রজেক্টে সাধারণ user, restaurant এবং delivery role-ভিত্তিক signup, login, food management, order এবং delivery flow আছে।

## প্রধান ফিচার

- User, restaurant এবং delivery account signup/login
- Restaurant food item add ও management
- Customer-এর জন্য food ও order flow
- Restaurant থেকে delivery request পাঠানো
- Delivery worker-এর জন্য available এবং active delivery dashboard
- Delivery accept ও complete করার ব্যবস্থা
- Thymeleaf template-based responsive UI
- SQLite database এবং JPA/Hibernate data access
- Local filesystem থেকে image upload ও serving

## Technology Stack

- Java 25
- Spring Boot 3.5.7
- Spring MVC
- Spring Data JPA / Hibernate
- SQLite 3
- Thymeleaf
- Maven
- Lombok

## প্রয়োজনীয় সফটওয়্যার

- JDK 25
- Maven (অথবা প্রজেক্টের included Maven Wrapper)

কমান্ড চালানোর আগে Java version পরীক্ষা করুন:

```powershell
java -version
```

## কীভাবে চালাবেন

প্রজেক্টের root folder-এ terminal খুলুন:

```powershell
.\mvnw.cmd spring-boot:run
```

অথবা Maven ব্যবহার করে:

```powershell
mvn spring-boot:run
```

অ্যাপ চালু হওয়ার পর ব্রাউজারে খুলুন:

```text
http://localhost:8080
```

## Database

SQLite database-এর configuration [application.properties](src/main/resources/application.properties)-এ আছে।

```properties
spring.datasource.url=jdbc:sqlite:src/main/resources/database/app.db
```

Database file:

```text
src/main/resources/database/app.db
```

`spring.jpa.hibernate.ddl-auto=update` configuration-এর কারণে application চলাকালে entity অনুযায়ী schema update করা হয়।

## File Upload

Uploaded image [uploads](uploads/) folder-এ সংরক্ষিত হয় এবং public static resource হিসেবে serve করা হয়। একক upload-এর সর্বোচ্চ size 10 MB।

## গুরুত্বপূর্ণ routes

| Route | Description |
| --- | --- |
| `/` | Home page |
| `/signup` | Account signup |
| `/login` | Login |
| `/user/dashboard` | User dashboard |
| `/user/orders` | User-এর order list |
| `/restaurant/dashboard` | Restaurant dashboard |
| `/restaurant/food/add` | নতুন food add |
| `/restaurant/foods` | Restaurant-এর food list |
| `/restaurant/orders` | Restaurant-এর orders |
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

## Build ও test

প্রজেক্ট build করতে:

```powershell
.\mvnw.cmd clean package
```

Test run করতে:

```powershell
.\mvnw.cmd test
```

## IDE setup

VS Code বা IntelliJ IDEA-তে project folder খুলুন। IntelliJ IDEA বা VS Code-এর Java Extension Pack JDK 25 detect ও configure করতে সহায়তা করবে।
