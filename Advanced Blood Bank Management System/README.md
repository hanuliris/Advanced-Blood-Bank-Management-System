# Advanced Blood Bank Management System

## Overview
The Advanced Blood Bank Management System is a Java Swing-based application designed to manage blood inventory, donor information, and handle emergency blood requests. It provides two secure portals: 
- **Admin Portal**: For inventory control and system oversight.
- **Donor Portal**: For registered donors to view data, history, and status.

## Features
- **User Authentication**: Secure login using hashed passwords (SHA-256) for different roles (Admin/Donor).
- **Donor Registration**: Allow new donors to easily register with the system.
- **Blood Stock Inventory Management**: Track and manage available blood groups.
- **Search Capabilities**: Find compatible blood for patients and emergencies.

## Technology Stack
- **Java 17+**: Core application language.
- **Swing (AWT/Swing)**: User interface framework.
- **MySQL**: Relational database for persistent storage.
- **Maven**: Build and dependency management (e.g., handles the MySQL Connector/J driver automatically).

## Project Structure
This application follows a standard Maven modular Java structure:
```text
Advanced Blood Bank Management System
├── src/main/java/com/bloodbank
│   ├── model/    # Core entities (Donor, User, BloodStock, EmergencyRequest)
│   ├── service/  # Business logic (Inventory Service, Auth Service, etc.)
│   ├── ui/       # Swing GUI windows and forms
│   └── util/     # Helper classes (DBConnection, PasswordHasher, etc.)
├── db/           # Database initialization scripts
├── pom.xml       # Maven dependencies and build configuration
└── README.md
```

## Database Setup
Check that you have a MySQL server installed and actively running on port 3306.

1. **Create the Database and Tables:**
   Locate the initialization script inside the `db` directory: `db/bloodbank.sql`.
   You will need a database named `bloodbank` on your server.
   Run the following or execute the `.sql` inside your preferred client (e.g., MySQL Workbench or phpMyAdmin):
   ```bash
   mysql -u root -p bloodbank < db/bloodbank.sql
   ```

2. **Configure Database Connection:**
   The connection configuration is stored in `src/main/java/com/bloodbank/util/DBConnection.java`. Update the JDBC URL, username, and password parameters if your MySQL server differs from the defaults below:
   - URL: `jdbc:mysql://localhost:3306/bloodbank`
   - User: `root`
   - Password: `(empty)`

## How to Build and Run

### Using IDE (IntelliJ / Eclipse / VS Code)
Simply open or import the directory as a standard Maven project. Your IDE will automatically download the required dependencies. Navigate to `src/main/java/com/bloodbank/ui/MainScreenUI.java` and click "Run".

### Using Command Line (Maven)
With Maven installed, execute these commands from the project root:

**To compile the application:**
```bash
mvn clean compile
```

**To run the application natively:**
```bash
mvn exec:java
```

**To build a packaged standalone Executable JAR:**
```bash
mvn clean package
```
*Your runnable output JAR will be available in the `target/` directory.*
