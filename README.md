# Java Task Manager
A desktop task management applicaiton built with Java JOptionpane and MySQL. It allows you to create, view, edit, remove tasks with changes and tasks stored in a database so they persist between sesssions.

## Features
- Create tasks with a name, description and priority level.
- View tasks and their details.
- Edit tasks by changing their name, description, priority level and completion status.
- Delete tasks with a confirmation prompt.
- Persistent storage with MySQL.
- Database Access through JDBC and prepared statements.

## Technologies Used
- Java (JDK)
- Java JOptionpane for GUI
- MySQL Server v8.0
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- Intellij IDEA

This project isn't a maven build but a standard Intellij Java project.

## Prerequisites

1. Install a JDK compatible with the project. This project uses OpenJDK 27.
2. Intellij IDEA: was used to open, configure, and run the project.
3. MySQL Server v8.0: The application expects a local MySQL server running on port 3306 by default.
4. MySQL Connector/J: The JDBC driver required for Java to communicate with MySQL.

## Database Setup
### 1. Start MySQL

Make sure MySQL server is installed from https://dev.mysql.com/downloads/mysql/ and running on your computer.
During Installation configure your MySQL root password or create a separate database user.

The default connection settings used are:
- Host: localhost
- Port: 3306
- Database: task_manager

If your server uses a diffirent host or port, update the JDBC URL in DatabaseConnection.java to fit yours.

### 2. Create the database and table.

1. Open MySQL Workbench and connect to your local MySQL Server.
2. Open the database_setup.sql file from the repository.
3. Execute the SQL script to create task_manager database and Tasks table.

### 3. Configure database Credentials

The application reads database credentials from enviornment varaiables, keeping passwords out of the source code.

In Intellij IDEA: 

1. Open Run -> Edit Configurations
2. Select the configuration used to run Main.
3. Add environment variables, using your own MySQL credentials:
   - DB_USER --- your MySQL username
   - DB_PASSWORD --- your MySQL password
4. Apply the changes and save the configuation
