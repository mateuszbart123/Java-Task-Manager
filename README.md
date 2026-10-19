# Java Task Manager
A desktop task management application built with Java JoptionPane and MySQL. It allows you to create, view, edit, remove tasks with changes and tasks stored in a database so they persist between sessions.

## Features
- Create tasks with a name, description and priority level.
- View tasks and their details.
- Edit tasks by changing their name, description, priority level and completion status.
- Delete tasks with a confirmation prompt.
- Persistent storage with MySQL.
- Database Access through JDBC and prepared statements.

## Technologies Used
- Java (JDK)
- Java JoptionPane for GUI
- MySQL Server v8.0
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- IntelliJ IDEA

This project isn't a maven build but a standard IntelliJ Java project.

## Prerequisites

1. Install a JDK compatible with the project. This project uses OpenJDK 27.
2. IntelliJ IDEA: was used to open, configure, and run the project.
3. MySQL Server v8.0: The application expects a local MySQL server running on port 3306 by default.
4. MySQL Connector/J: The JDBC driver required for Java to communicate with MySQL.

## Database Setup
### 1. Start MySQL

Make sure MySQL server v8.0 is installed from https://dev.mysql.com/downloads/mysql/ and running on your computer.
During Installation configure your MySQL root password or create a separate database user.

The default connection settings used are:
- Host: localhost
- Port: 3306
- Database: task_manager

If your server uses a different host or port, update the JDBC URL in DatabaseConnection.java to fit yours.

### 2. Create the database and table.

1. Open MySQL Workbench and connect to your local MySQL Server.
2. Open the database_setup.sql file from the repository.
3. Execute the SQL script to create task_manager database and Tasks table.

### 3. Configure database Credentials

The application reads database credentials from environment varaiables, keeping passwords out of the source code.

In IntelliJ IDEA: 

1. Open Run -> Edit Configurations.
2. Select the configuration used to run Main.
3. Add environment variables, using your own MySQL credentials:
   - DB_USER --- your MySQL username
   - DB_PASSWORD --- your MySQL password
4. Apply the changes and save the configuration.

## MySQL Connector/J Setup

The connector is included as a zip in this repository.

1. Extract the zip file on to your computer.
2. In IntelliJ IDEA, go to File -> Project Structure -> Modules -> Dependencies.
3. Click + -> Jars or Directories and select the extracted mysql-connector-j-26.7.0.jar file.
4. Apply the changes. The driver is now configured.

## Running the project
1. Open the project in IntelliJ IDEA.
2. Make sure MySQL Server is running and the database has been created.
3. Check that the MySQL Connector/J driver is added to the project.
4. Configure the DB_USER and DB_PASSWORD environment variables in the run configuration.
5. Open Main.java and click Run

The application should launch and open the task manager menu.

## Project Structure

- `src/` — Java source code.
- `src/database/` — Database connection and data access classes.
- `src/model/` — Task model.
- `src/ui/` — User interface and menu.
- `Main` — Runs the application.
