# 💰 Expense Tracker

A simple **Expense Tracker** built with **Spring Boot**, **Java**, and **PostgreSQL**. This project allows users to track their expenses, categorize them, and view summaries of their financial activity.

## 🚀 Features
- 🔐 User authentication (Spring Security & JWT)
- 📊 Track income and expenses
- 🏷️ Categorize transactions
- 📅 View expenses by date range
- 💾 Persistent storage with PostgreSQL
- 🖥️ REST API for CRUD operations

---

## 🛠️ Technologies Used
- **Java 21**
- **Spring Boot 3.3**
- **Spring Security (JWT authentication)**
- **PostgreSQL**
- **Hibernate (JPA)**
- **HikariCP (Connection Pooling)**
- **Gradle** as the build tool

---

## 📂 Project Structure

ExpenseTracker/ 
│── src/ │ 
├── main/java/org/example/ (Main application) 
│ ├── main/resources/ │ │ 
├── application.properties (Database config) 
│ │ ├── static/ (Frontend assets if any)
│ │ ├── templates/ (Thymeleaf templates if applicable)
│── build.gradle (Gradle dependencies)
│── README.md (You're reading this!)



---

## 🔧 Setup & Installation

### 1️⃣ Clone the Repository
bash
git clone https://github.com/your-username/ExpenseTracker.git
cd ExpenseTracker


2️⃣ Set Up PostgreSQL Database

Make sure you have PostgreSQL running and create a database:

CREATE DATABASE expense_tracker;
CREATE USER your_db_user WITH ENCRYPTED PASSWORD 'your_db_password';
GRANT ALL PRIVILEGES ON DATABASE expense_tracker TO your_db_user;

3️⃣ Configure .env or application.properties

Replace the following with your actual credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

4️⃣ Build & Run

Using Gradle:

./gradlew bootRun

Using Maven:

mvn spring-boot:run

🛠️ API Endpoints
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login and get JWT token
GET	/api/expenses	Get all expenses
POST	/api/expenses	Add a new expense
PUT	/api/expenses/{id}	Update an expense
DELETE	/api/expenses/{id}	Delete an expense
🏗️ Future Improvements

    📈 Charts and reports for expense analysis
    📲 Frontend with React/Vue/Angular
    📅 Recurring expenses & budget planning
    🌍 Multi-currency support

🤝 Contributing

If you want to contribute:

    Fork this repo
    Create a new branch (feature/my-new-feature)
    Commit your changes
    Push to the branch
    Create a pull request

📝 License

This project is licensed under the MIT License - see LICENSE.md for details.
📩 Contact

For any questions, feel free to reach out:

    Email: pana.alatzas@gmail.com
    GitHub: https://github.com/PanagiotisAlas
    LinkedIn: https://www.linkedin.com/in/panagiotis-alatzas-1b295233a/overlay/about-this-profile/

🔥 If you find this project useful, please ⭐ it on GitHub!


---


## ⚠️ Disclaimer
This project was created as part of a masterclass/tutorial and is for **learning purposes only**.  
Some parts of the code are based on the original tutorial, and I do not claim full ownership of this work.  
If you are the author of the original course and have any concerns, please contact me.
