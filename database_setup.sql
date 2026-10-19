CREATE DATABASE IF NOT EXISTS task_manager;

USE task_manager;

CREATE TABLE IF NOT EXISTS Tasks (
    task_id INT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(100) NOT NULL,
    task_desc TEXT,
    task_complete BOOLEAN NOT NULL DEFAULT FALSE,
    task_priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM'
);
