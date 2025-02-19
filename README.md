# 🚀 CrowdFunding Platform

## 📖 Overview

Welcome to the **Crowdfunding Platform**! This web application enables users to **create, explore, and donate** to innovative ideas. The platform also includes an **admin dashboard** for managing statistics and features **asynchronous report &#40;CSV files&#41; generation**.

Inspired by **best coding practices, clean architecture, and design patterns**, this platform follows the **MVC** paradigm to ensure a **structured, maintainable, and efficient** system. It incorporates robust validation, secure transactions, and an intuitive user interface.

## 🏗️ Technologies Used
- **Backend:** Java 21, Spring Boot, Hibernate, Flyway, MapStruct, Specification API, Lombok, Spring Security, Spring Data JPA, CompletableFuture API
- **Frontend:** HTML5, CSS3, JavaScript, jQuery, Thymeleaf
- **Database:** MySQL, Flyway (Database Migrations)
- **Security:** Spring Boot Security, AES Encryption for sensitive data
- **Design Patterns:** Singleton, Strategy (for report generation)
- **Testing:** JUnit
- **Build & Deployment:** Maven, Docker

## ✨ Features
### 🔑 Authentication & User Management
- **Login, Sign Up, and Logout** with proper validation
- **Role-based access:** `USER` and `ADMIN` roles
- **Admins can:**
    - View platform statistics
    - Manage users (promote to ADMIN, deactivate, activate, delete users)

### 💡 Idea Management
- **Users can:**
    - **Create ideas** with name, description, goal amount, category, and an image
    - **View their created ideas**
    - **Delete their ideas**
- **Exploring ideas:**
    - Filtering by category, status, search query and sorting ASC and DESC by any field
    - Viewing details such as creator’s name, goal amount, collected amount, status, creation date, number of donations, and category

### 💰 Donations with AES Encryption
- **Users can donate to ideas** securely with validation checks:
    - **Minimum donation amount** enforced
    - **Cannot exceed remaining goal amount**
    - **Cannot donate to own idea**
    - **Cannot donate to completed ideas**
    - **AES encryption** of card details (Card Number & CVV)

### 📊 Admin Panel & Reports
- **Admins can generate reports asynchronously (CSV files):**
    - Implemented using **Java multithreading & Strategy Design Pattern**
    - Reports can have **IN_PROGRESS, ERROR, or COMPLETED** status
    - Downloading and deleting CSV reports available
- **Statistics Dashboard**
    - Number of active/inactive users
    - Completed projects count
    - Full user list with management features

### ✅ Custom Validations
- **Email uniqueness check**
- **Form validation with regex patterns** (passwords, emails, amount restrictions)
- **Empty/blank input prevention** with UI messages

## 🗄️ Database Model
Below is the **database schema** used in the project:

![Database Model](/crowdfundingFinalDBModel.jpg)

## 🚀 Setup Instructions
1. **Clone this repository:**
   ```bash
   git clone <repository-url>
   cd crowdfunding-platform
    ```